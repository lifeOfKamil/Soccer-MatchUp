package com.kamil.pickup.service;

import com.kamil.pickup.model.Field;
import com.kamil.pickup.repository.FieldRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class FieldService {
    private final FieldRepository repo;

    public FieldService(FieldRepository repo) {
        this.repo = repo;
    }

    public List<Field> findAll() {
        return repo.findAll();
    }

    public Field findByName(String name) {
        return repo.findByName(name).orElseThrow(() -> new NoSuchElementException("Field not found with name " + name));
    }

    public Optional<Field> findByAddress(String address) {
        return repo.findByAddress(address);
    }

    public boolean existsByName(String name) {
        return repo.existsByName(name);
    }

    public boolean existsByAddress(String address) {
        return repo.existsByAddress(address);
    }

    public List<Field> searchByName(String fragment) {
        return repo.findByNameContainingIgnoreCase(fragment);
    }

    public List<Field> searchByAddress(String fragment) {
        return repo.findByAddressContainingIgnoreCase(fragment);
    }

    public Field findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new NoSuchElementException("Field not found with id " + id));
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    public void deleteByName(String name) {
        repo.deleteByName(name);
    }

    public Field save(Field f) {
        return repo.save(f);
    }
}
