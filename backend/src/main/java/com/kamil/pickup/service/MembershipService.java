package com.kamil.pickup.service;

import com.kamil.pickup.model.Group;
import com.kamil.pickup.model.Membership;
import com.kamil.pickup.model.Role;
import com.kamil.pickup.model.User;
import com.kamil.pickup.repository.GroupRepository;
import com.kamil.pickup.repository.MembershipRepository;
import com.kamil.pickup.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MembershipService {
    private final MembershipRepository memberRepo;
    private final UserRepository userRepo;
    private final GroupRepository groupRepo;

    public MembershipService(MembershipRepository memberRepo, UserRepository userRepo, GroupRepository groupRepo) {
        this.memberRepo = memberRepo;
        this.userRepo = userRepo;
        this.groupRepo = groupRepo;
    }

    public Membership addUserToGroup(Long userId, Long groupId, Role role) {
        User user = userRepo.findById(userId).orElseThrow();
        Group group = groupRepo.findById(groupId).orElseThrow();
        if (memberRepo.existsByUserAndGroup(user, group)) {
            throw new IllegalStateException("Already a member");
        }
        Membership m = new Membership(user, group, role);
        return memberRepo.save(m);
    }

    public void removeUserFromGroup(Long userId, Long groupId) {
        memberRepo.deleteByUserIdAndGroupId(userId, groupId);
    }

    public Membership changeRole(Long userId, Long groupId, Role newRole) {
        Membership m = memberRepo.findByUserIdAndGroupId(userId, groupId).orElseThrow();
        m.setRole(newRole);
        return m;
    }

    public List<Membership> findAll() {
        return memberRepo.findAll();
    }

    public Membership getById(Long id) {
        return memberRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Membership not found with id " + id));
    }

    public List<User> listMembers(Long groupId) {
        return memberRepo.findByGroupId(groupId).stream().map(Membership::getUser).toList();
    }
}
