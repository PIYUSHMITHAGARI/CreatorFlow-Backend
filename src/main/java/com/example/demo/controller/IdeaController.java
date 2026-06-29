package com.example.demo.controller;

import com.example.demo.model.Idea;
import com.example.demo.service.IdeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ideas")
@CrossOrigin(origins = "http://localhost:5173")
public class IdeaController {

    @Autowired
    private IdeaService ideaService;

    // CREATE IDEA
    @PostMapping
    public Idea createIdea(@RequestBody Idea idea) {

        return ideaService.createIdea(idea);
    }

    // GET USER IDEAS
    @GetMapping("/{userId}")
    public List<Idea> getIdeasByUser(
            @PathVariable Long userId) {

        return ideaService.getIdeasByUser(userId);
    }

    // UPDATE IDEA
    @PutMapping("/{id}")
    public ResponseEntity<Idea> updateIdea(
            @PathVariable Long id,
            @RequestBody Idea idea) {

        return ResponseEntity.ok(
                ideaService.updateIdea(id, idea)
        );
    }

    // DELETE IDEA
    @DeleteMapping("/{id}")
    public String deleteIdea(
            @PathVariable Long id) {

        ideaService.deleteIdea(id);

        return "Idea Deleted Successfully";
    }
}