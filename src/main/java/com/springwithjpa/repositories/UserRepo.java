package com.springwithjpa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springwithjpa.entities.User;


public interface UserRepo extends JpaRepository<User, Integer> {

  Optional<User> findByUsername(String username);
}
