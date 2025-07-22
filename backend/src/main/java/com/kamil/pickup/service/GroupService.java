package com.kamil.pickup.service;

import com.kamil.pickup.model.Group;
import com.kamil.pickup.model.User;
import com.kamil.pickup.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class GroupService {
    private final GroupRepository groupRepo;
    private final MembershipService membershipService;

    public GroupService(GroupRepository groupRepo, MembershipService membershipService) {
        this.groupRepo = groupRepo;
        this.membershipService = membershipService;
    }

    public Group createGroup(String name, String description, User owner) {
        if (groupRepo.findByName(name).isPresent()) {
            throw new IllegalArgumentException("A group with that name already exists");
        }
        Group g = new Group(name, description, owner);
        return groupRepo.save(g);
    }

    public Group findById(Long id) {
        return groupRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Group not found"));
    }

    public Optional<Group> findByName(String name) {
        return groupRepo.findByName(name);
    }

    public Group updateName(Long groupId, String newName) {
        if (groupRepo.findById(groupId).isEmpty()) {
            throw new IllegalArgumentException("Group does not exist");
        }
        Group g = findById(groupId);
        g.setName(newName);
        return groupRepo.save(g);
    }

    public Group updateDescription(Long groupId, String newDescription) {
        Group g = findById(groupId);
        g.setDescription(newDescription);
        return groupRepo.save(g);
    }

    public void deleteById(Long groupId) {
        groupRepo.deleteById(groupId);
    }

    public void deleteByName(String name) {
        groupRepo.deleteByName(name);
    }

    public List<Group> listOwnedBy(User owner) {
        return groupRepo.findByOwner(owner);
    }

    public List<User> listMembers(Long groupId) {
        // delegates into your MembershipService / MembershipRepository
        return membershipService.listMembers(groupId);
    }

    public List<Group> findAll() {
        return groupRepo.findAll();
    }
}
