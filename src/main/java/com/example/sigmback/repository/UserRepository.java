package com.example.sigmback.repository;

import java.util.Optional;

import com.example.sigmback.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);

}
