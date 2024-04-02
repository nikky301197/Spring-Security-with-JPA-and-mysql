package com.springwithjpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springwithjpa.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>  {

}
