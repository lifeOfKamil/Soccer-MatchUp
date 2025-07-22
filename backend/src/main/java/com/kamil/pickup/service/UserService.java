package com.kamil.pickup.service;

import com.kamil.pickup.model.User;
import com.kamil.pickup.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repo;
    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepository repo,
                       BCryptPasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    public User register(String email, String rawPassword, String displayName) {
        if (repo.existsByEmail(email)) {
            throw new IllegalArgumentException("That email is already in use");
        }
        String hash = encoder.encode(rawPassword);
        User u = new User(email, hash, false, displayName);
        return repo.save(u);
    }

    public User createGuest(String email, String displayName) {
        if (repo.existsByEmail(email)) {
            throw new IllegalArgumentException("That email is already in use");
        }
        User guest = new User(email, null, true, displayName);
        return repo.save(guest);
    }

    public Optional<User> findByEmail(String email) {
        return repo.findByEmail(email);
    }

    public Optional<User> findById(Long id) {
        return repo.findById(id);
    }

    public User updateDisplayName(Long userId, String newName) {
        User u = repo.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found"));
        u.setDisplayName(newName);
        return repo.save(u);
    }

    public void changePassword(Long userId, String newRawPassword) {
        User u = repo.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found"));
        u.setPasswordHash(encoder.encode(newRawPassword));
    }

    public List<User> getMembersOfGroup(Long groupId) {
        return repo.findAllByGroupId(groupId);
    }

    public List<User> findAll() {
        return repo.findAll();
    }
}