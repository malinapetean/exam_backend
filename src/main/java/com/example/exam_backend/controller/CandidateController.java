package com.example.exam_backend.controller;

import com.example.exam_backend.model.Candidate;
import com.example.exam_backend.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidates")
@CrossOrigin(origins = "http://localhost:3000")
public class CandidateController {

    @Autowired
    private CandidateService service;

    @GetMapping
    public List<Candidate> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Candidate add(@RequestBody Candidate candidate) {
        return service.add(candidate);
    }

    @PutMapping
    public Candidate update(@RequestBody Candidate candidate) {
        return service.update(candidate);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/{id}")
    public Candidate getById(@PathVariable Long id) {
        return service.getById(id).orElse(null);
    }
}
