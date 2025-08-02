package com.kamil.pickup.service;

import com.kamil.pickup.model.Team;
import com.kamil.pickup.model.User;
import com.kamil.pickup.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class GroupService {
    private final TeamRepository groupRepo;
    private final MembershipService membershipService;

    public GroupService(TeamRepository groupRepo, MembershipService membershipService) {
        this.groupRepo = groupRepo;
        this.membershipService = membershipService;
    }

    public Team createGroup(String name, String description, User owner) {
        if (groupRepo.findByName(name).isPresent()) {
            throw new IllegalArgumentException("A group with that name already exists");
        }
        Team g = new Team(name, description, owner);
        return groupRepo.save(g);
    }

    public Team findById(Long id) {
        return groupRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Group not found"));
    }

    public Optional<Team> findByName(String name) {
        return groupRepo.findByName(name);
    }

    public Team updateName(Long groupId, String newName) {
        if (groupRepo.findById(groupId).isEmpty()) {
            throw new IllegalArgumentException("Group does not exist");
        }
        Team g = findById(groupId);
        g.setName(newName);
        return groupRepo.save(g);
    }

    public Team updateDescription(Long groupId, String newDescription) {
        Team g = findById(groupId);
        g.setDescription(newDescription);
        return groupRepo.save(g);
    }

    public void deleteById(Long groupId) {
        groupRepo.deleteById(groupId);
    }

    public void deleteByName(String name) {
        groupRepo.deleteByName(name);
    }

    public List<Team> listOwnedBy(User owner) {
        return groupRepo.findByOwner(owner);
    }

    public List<User> listMembers(Long groupId) {
        // delegates into your MembershipService / MembershipRepository
        return membershipService.listMembers(groupId);
    }

    public List<Team> findAll() {
        return groupRepo.findAll();
    }
}
