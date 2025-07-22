package com.kamil.pickup.repository;

import com.kamil.pickup.model.Field;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FieldRepository extends JpaRepository<Field, Long> {
    Optional<Field> findByName(String name);
    void deleteByName(String name);

    boolean existsByName(String name);
    boolean existsByAddress(String address);

    Optional<Field> findByAddress(String address);

    List<Field> findByNameContainingIgnoreCase(String namePart);
    List<Field> findByAddressContainingIgnoreCase(String addressPart);
}
