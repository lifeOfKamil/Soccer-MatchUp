package com.kamil.pickup.repository;

import com.kamil.pickup.model.Team;
import com.kamil.pickup.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Team, Long> {
    Optional<Team> findByName(String name);
    void deleteByName(String name);

    List<Team> findByOwner(User owner);
    List<Team> findByOwnerId(Long ownerId);

    // find all groups a user belongs to via membership
    @Query("SELECT m.group FROM Membership m WHERE m.user.id = :userId")
    List<Team> findAllByMemberId(@Param("userId") Long userId);

    List<Team> findByNameContainingIgnoreCase(String fragment);

    long countByOwnerId(Long ownerId);
}
