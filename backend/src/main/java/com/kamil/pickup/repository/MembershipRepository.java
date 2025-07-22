package com.kamil.pickup.repository;

import com.kamil.pickup.model.Group;
import com.kamil.pickup.model.Membership;
import com.kamil.pickup.model.Role;
import com.kamil.pickup.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MembershipRepository extends JpaRepository<Membership, Long> {
    Boolean existsByUserAndGroup(User user, Group group);
    void deleteByUserIdAndGroupId(Long userId, Long groupId);
    Optional<Membership> findByUserIdAndGroupId(Long userId, Long groupId);
    Optional<Membership> findByGroupIdAndRole(Long groupId, Role role);
    long countByGroupIdAndRole(Long groupId, Role role);
    List<Membership> findByGroupId(Long groupId);
    List<Membership> findByUserId(Long userId);
}
