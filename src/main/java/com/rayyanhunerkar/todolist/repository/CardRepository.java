package com.rayyanhunerkar.todolist.repository;

import com.rayyanhunerkar.todolist.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CardRepository extends JpaRepository<Card, UUID> {
    Optional<Card> findByTitle(String title);
}