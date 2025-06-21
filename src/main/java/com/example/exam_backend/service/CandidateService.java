package com.example.exam_backend.service;

import com.example.exam_backend.model.Candidate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CandidateService {

    private final List<Candidate> candidates = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong();

    public CandidateService() {
        add(new Candidate( "Elena Lasconi", "Experienced leader focused on education reform.",
                "https://www.revista-femeia.ro/wp-content/uploads/2024/09/Elena-Lasconi.jpg", "USR Party"));

        add(new Candidate("George Simion", "Entrepreneur with a passion for nationalism and traditional values.",
                "https://upload.wikimedia.org/wikipedia/commons/f/f8/Portret_George_Simion_%2812%29_%28decupat%29.jpg", "AUR Party"));

        add(new Candidate( "Nicușor Dan", "Centrist, pro‑EU mayor of Bucharest and recent presidential candidate.",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTsrgk-mwhClLEJNNdJHLxa1oniB-7xB672dg&s", "Independent / Pro‑EU"));

        add(new Candidate( "Marcel Ciolacu", "Leader of PSD and former Prime Minister (2023–2025), known for economic patriotism.",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ2lU5I_HJdGiA_KGU__7LD-BRnl9kPXXis7g&s", "PSD Party"));

        add(new Candidate( "Alina Gorghiu", "Lawyer and former leader of PNL, former Senate president.",
                "https://www.caleaeuropeana.ro/wp-content/uploads/2014/12/alina-gorghiu-portret.jpg", "PNL Party"));
    }


    public List<Candidate> getAll() {
        return candidates;
    }

    public Candidate add(Candidate c) {
        c.setId(idGenerator.incrementAndGet());
        candidates.add(c);
        return c;
    }

    public void delete(Long id) {
        candidates.removeIf(c -> c.getId().equals(id));
    }

    public Candidate update(Candidate updated) {
        delete(updated.getId());
        candidates.add(updated);
        return updated;
    }

    public Optional<Candidate> getById(Long id) {
        return candidates.stream().filter(c -> c.getId().equals(id)).findFirst();
    }
}
