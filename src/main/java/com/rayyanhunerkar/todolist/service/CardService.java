package com.rayyanhunerkar.todolist.service;

import com.rayyanhunerkar.todolist.POJO.Cards.CardRequest;
import com.rayyanhunerkar.todolist.POJO.Cards.CardResponse;
import com.rayyanhunerkar.todolist.POJO.Cards.CardUpdateRequest;
import com.rayyanhunerkar.todolist.POJO.Response.Response;
import com.rayyanhunerkar.todolist.model.Card;
import com.rayyanhunerkar.todolist.model.State;
import com.rayyanhunerkar.todolist.model.User;
import com.rayyanhunerkar.todolist.repository.CardRepository;
import com.rayyanhunerkar.todolist.repository.StateRepository;
import com.rayyanhunerkar.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class CardService {
    @Autowired
    private final CardRepository cardRepository;
    @Autowired
    private final StateRepository stateRepository;
    @Autowired
    private final UserRepository userRepository;

    public CardService(CardRepository cardRepository, StateRepository stateRepository, UserRepository userRepository) {
        this.cardRepository = cardRepository;
        this.stateRepository = stateRepository;
        this.userRepository = userRepository;
    }

    public Response<Object> createCard(final CardRequest cardRequest) throws Exception {

        Card card;
        Optional<State> state;
        State stateEntity;
        Optional<User> assignedTo;
        User assignedToEntity = null;
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        state = stateRepository.findById(
                cardRequest.getState()
        );

        if (state.isEmpty()) {
            return Response.builder()
                    .message("State does not exist")
                    .build();
        }

        if (cardRequest.getAssigned_to() != null) {
            assignedTo = userRepository.findById(cardRequest.getAssigned_to());
            if (assignedTo.isEmpty()) {
                return Response.builder()
                        .message("User does not exist")
                        .build();
            }
            assignedToEntity = assignedTo.get();
        }


        stateEntity = state.get();

        try {
            card = cardRepository.save(Card.builder()
                    .title(cardRequest.getTitle())
                    .description(cardRequest.getDescription())
                    .deadline(cardRequest.getDeadline())
                    .state(stateEntity)
                    .createdBy(user)
                    .assignedTo(assignedToEntity)
                    .team(user.getTeam())
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

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Card> cards = cardRepository.findAllByTeamId(user.getTeam().getId());


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

    public Response<Object> getCard(UUID id) {
        Optional<Card> card = cardRepository.findById(id);

        if (card.isEmpty()) {
            return null;
        }

        CardResponse response = CardResponse.builder()
                .id(card.get().getId())
                .title(card.get().getTitle())
                .description(card.get().getDescription())
                .deadline(card.get().getDeadline())
                .state_id(card.get().getState().getId())
                .createdBy(card.get().getCreatedBy().getId())
                .createdOn(card.get().getCreatedOn())
                .modifiedOn(card.get().getModifiedOn())
                .build();

        return Response.builder()
                .data(response)
                .message("Task fetched successfully")
                .build();
    }

    public Response<Object> updateCard(UUID id, CardUpdateRequest request) {

        Optional<Card> card = cardRepository.findById(id);
        Optional<State> state = stateRepository.findById(request.getState());

        if (card.isEmpty() || state.isEmpty()) {
            return null;
        }

        Card cardEntity = card.get();

        cardEntity.setTitle(request.getTitle() != null ? request.getTitle() : cardEntity.getTitle());
        cardEntity.setDescription(request.getDescription() != null ? request.getDescription() : cardEntity.getDescription());
        cardEntity.setDeadline(request.getDeadline() != null ? request.getDeadline() : cardEntity.getDeadline());
        cardEntity.setState(state.get());

        cardEntity = cardRepository.save(cardEntity);

        CardResponse response = CardResponse.builder()
                .id(cardEntity.getId())
                .title(cardEntity.getTitle())
                .description(cardEntity.getDescription())
                .deadline(cardEntity.getDeadline())
                .createdBy(cardEntity.getCreatedBy().getId())
                .state_id(state.get().getId())
                .createdOn(cardEntity.getCreatedOn())
                .modifiedOn(cardEntity.getModifiedOn())
                .build();

        return Response.builder()
                .data(response)
                .message("Task created successfully!")
                .build();
    }

}
