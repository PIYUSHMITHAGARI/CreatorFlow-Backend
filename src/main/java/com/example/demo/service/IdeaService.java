package com.example.demo.service;

import com.example.demo.model.Idea;
import com.example.demo.repository.IdeaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdeaService {
	
	@Autowired
	private LimitService limitService;

    @Autowired
    private IdeaRepository ideaRepository;

    // CREATE IDEA
    public Idea createIdea(
            Idea idea
    ) {

        if (!limitService.canCreate(
                idea.getUserId()
        )) {

            throw new RuntimeException(
                    "You have reached your limit. Upgrade to Premium for 50 uploads/ideas."
            );
        }

        return ideaRepository.save(
                idea
        );
    }

    // GET ALL IDEAS OF USER
    public List<Idea> getIdeasByUser(Long userId) {
        return ideaRepository.findByUserId(userId);
    }

    // UPDATE IDEA
    public Idea updateIdea(Long id, Idea updatedIdea) {

        Idea idea = ideaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Idea not found"));

        idea.setTitle(updatedIdea.getTitle());
        idea.setDescription(updatedIdea.getDescription());
        idea.setCategory(updatedIdea.getCategory());
        idea.setStatus(updatedIdea.getStatus());

        return ideaRepository.save(idea);
    }

    // DELETE IDEA
    public void deleteIdea(Long id) {

        ideaRepository.deleteById(id);
    }
}