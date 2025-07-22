package com.kamil.pickup.repository;

import com.kamil.pickup.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

    Optional<User> findByDisplayName(String displayName);

    List<User> findByIsGuest(Boolean isGuest);

    @Query("SELECT m.user FROM Membership m WHERE m.group.id = :groupId")
    List<User> findAllByGroupId(@Param("groupId") Long groupId);
}
