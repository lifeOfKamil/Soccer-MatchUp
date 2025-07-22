package com.kamil.pickup.repository;

import com.kamil.pickup.model.Match;
import com.kamil.pickup.model.MatchStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {
    // Find all matches by GroupID
    List<Match> findByGroupId(Long groupId);

    // Find all matches by specific field
    List<Match> findByFieldId(Long fieldId);

    // Find all matches after certain date/time
    List<Match> findByMatchDateTimeAfter(LocalDateTime dateTime);

    // Find upcoming matches for group
    List<Match> findByGroupIdAndMatchDateTimeAfter(Long groupId, LocalDateTime dateTime);

    // Find matches by status
    List<Match> findByStatus(MatchStatus status);
}
