package com.kamil.pickup.repository;

import com.kamil.pickup.model.Group;
import com.kamil.pickup.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {
    Optional<Group> findByName(String name);
    void deleteByName(String name);

    List<Group> findByOwner(User owner);
    List<Group> findByOwnerId(Long ownerId);

    // find all groups a user belongs to via membership
    @Query("SELECT m.group FROM Membership m WHERE m.user.id = :userId")
    List<Group> findAllByMemberId(@Param("userId") Long userId);

    List<Group> findByNameContainingIgnoreCase(String fragment);

    long countByOwnerId(Long ownerId);
}
