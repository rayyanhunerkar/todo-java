package com.rayyanhunerkar.todolist.repository;

import com.rayyanhunerkar.todolist.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StateRepository extends JpaRepository<State, UUID> {

    Optional<State> findByName(String name);
    List<State> findAllByTeamId(UUID team_id);
}
