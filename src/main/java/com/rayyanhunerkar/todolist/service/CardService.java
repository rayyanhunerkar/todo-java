package com.rayyanhunerkar.todolist.service;

import com.rayyanhunerkar.todolist.POJO.Cards.CardRequest;
import com.rayyanhunerkar.todolist.POJO.Cards.CardResponse;
import com.rayyanhunerkar.todolist.POJO.Response.Response;
import com.rayyanhunerkar.todolist.model.Card;
import com.rayyanhunerkar.todolist.model.State;
import com.rayyanhunerkar.todolist.model.User;
import com.rayyanhunerkar.todolist.repository.CardRepository;
import com.rayyanhunerkar.todolist.repository.StateRepository;
import com.rayyanhunerkar.todolist.util.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class CardService {
    @Autowired
    private final CardRepository cardRepository;
    @Autowired
    private final StateRepository stateRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public CardService(CardRepository cardRepository, StateRepository stateRepository) {
        this.cardRepository = cardRepository;
        this.stateRepository = stateRepository;
    }

    public Response<Object> createCard(final CardRequest cardRequest) throws Exception {

        Card card;
        Optional<State> state;
        State stateEntity;
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        state = stateRepository.findById(
                cardRequest.getState()
        );

        if (state.isEmpty()) {
            return Response.builder()
                    .message("State does not exist")
                    .build();
        }

        stateEntity = state.get();

        try {
            card = cardRepository.save(Card.builder()
                    .title(cardRequest.getTitle())
                    .description(cardRequest.getDescription())
                    .deadline(cardRequest.getDeadline())
                    .state(stateEntity)
                    .createdBy(user)
                    .modifiedOn(new Timestamp(new Date().getTime()))
                    .createdOn(new Date())
                    .build()
            );

        } catch (Exception e) {
            throw new Exception(e);
        }

        CardResponse response = CardResponse.builder()
                .id(card.getId())
                .title(card.getTitle())
                .description(card.getDescription())
                .deadline(card.getDeadline())
                .createdBy(card.getCreatedBy().getId())
                .state_id(stateEntity.getId())
                .createdOn(card.getCreatedOn())
                .modifiedOn(card.getModifiedOn())
                .build();

        return Response.builder()
                .data(response)
                .message("Task created successfully!")
                .build();
    }

    public Response<Object> getCards() {

        List<Card> cards = cardRepository.findAll();

        List<CardResponse> responses = cards.stream()
                .map(card -> {
                    State state = card.getState();
                    return CardResponse.builder()
                            .id(card.getId())
                            .title(card.getTitle())
                            .description(card.getDescription())
                            .state_id(state.getId())
                            .createdOn(card.getCreatedOn())
                            .modifiedOn(card.getModifiedOn())
                            .build();
                })
                .toList();

        return Response.builder()
                .data(responses)
                .message("Tasks retrieved successfully")
                .build();
    }


}
