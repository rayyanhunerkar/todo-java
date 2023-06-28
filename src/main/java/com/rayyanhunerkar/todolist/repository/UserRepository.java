package com.rayyanhunerkar.todolist.repository;

import com.rayyanhunerkar.todolist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
