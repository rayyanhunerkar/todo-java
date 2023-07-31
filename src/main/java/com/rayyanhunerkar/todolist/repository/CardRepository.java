package com.rayyanhunerkar.todolist.repository;

import com.rayyanhunerkar.todolist.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface CardRepository extends JpaRepository<Card, UUID> {
    Optional<Card> findByTitle(String title);
    List<Card> findAllByStateId(UUID state_id);
    List<Card> findAllByTeamId(UUID team_id);
}
