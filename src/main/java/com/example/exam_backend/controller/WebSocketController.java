package com.example.exam_backend.controller;

import com.example.exam_backend.model.Candidate;
import com.example.exam_backend.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/api/generator")
public class WebSocketController {

    @Autowired
    private CandidateService service;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    private boolean generating = false;
    private final String[] parties = {"USR Party", "AUR Party", "PSD Party", "PNL Party", "Independent / Pro‑EU"};
    private final Random random = new Random();

    @PostMapping("/start")
    public ResponseEntity<Void> startGenerator() {
        generating = true;
        return ResponseEntity.ok().build();
    }

    @PostMapping("/stop")
    public ResponseEntity<Void> stopGenerator() {
        generating = false;
        return ResponseEntity.ok().build();
    }


    @Scheduled(fixedRate = 2000)
    public void generateFake() {
        if (generating) {
            Candidate c = new Candidate();
            c.setName("Auto " + System.currentTimeMillis());
            c.setParty(parties[random.nextInt(parties.length)]);
            c.setDescription("Auto-generated candidate");
            c.setImage("https://via.placeholder.com/150");
            Candidate added = service.add(c);
            messagingTemplate.convertAndSend("/topic/candidates", added);
        }
    }
}
