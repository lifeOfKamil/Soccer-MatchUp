package com.kamil.pickup.repository;

import com.kamil.pickup.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;


@SpringBootTest
@ActiveProfiles("dev")
@AutoConfigureTestDatabase(replace = NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRepositoryIntegrationTest {

  @Autowired
  private UserRepository users;

  @Test
  void seedDataIsPresent() {
    List<User> all = users.findAll();
    assertFalse(all.isEmpty(), "Seed should have inserted at least one user");
  }

  
}