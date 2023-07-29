package com.rayyanhunerkar.todolist.repository;

import com.rayyanhunerkar.todolist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    List<User> findByIdIn(List<UUID> ids);
    List<User> findAllByTeamId(UUID team_id);
}
