package com.kamil.pickup.service;

import com.kamil.pickup.model.Match;
import com.kamil.pickup.model.MatchStatus;
import com.kamil.pickup.repository.MatchRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MatchService {
    private final MatchRepository repo;

    public MatchService(MatchRepository repo) {
        this.repo = repo;
    }

    public List<Match> findAll() {
        return repo.findAll();
    }

    public Match getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new NoSuchElementException("Match not found with ID " + id));
    }

    public Match save(Match match) {
        return repo.save(match);
    }

    public void deleteById(Long id) {
        if (!repo.existsById(id)) {
            throw new NoSuchElementException("Cannot delete - no match with id " + id);
        }
        repo.deleteById(id);
    }

    public List<Match> findByGroup(Long groupId) {
        return repo.findByGroupId(groupId);
    }

    public List<Match> findByField(Long fieldId) {
        return repo.findByFieldId(fieldId);
    }

    public List<Match> findUpcomingForGroup(Long groupId) {
        return repo.findByGroupIdAndMatchDateTimeAfter(groupId, LocalDateTime.now());
    }

    public List<Match> findUpcoming() {
        return repo.findByMatchDateTimeAfter(LocalDateTime.now());
    }

    public List<Match> findByStatus(MatchStatus status) {
        return repo.findByStatus(status);
    }

}
