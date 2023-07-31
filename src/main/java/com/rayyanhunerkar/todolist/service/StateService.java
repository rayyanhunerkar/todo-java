package com.rayyanhunerkar.todolist.service;

import com.rayyanhunerkar.todolist.POJO.Cards.CardResponse;
import com.rayyanhunerkar.todolist.POJO.Response.Response;
import com.rayyanhunerkar.todolist.POJO.State.StateNoTasksResponse;
import com.rayyanhunerkar.todolist.POJO.State.StateRequest;
import com.rayyanhunerkar.todolist.POJO.State.StateResponse;
import com.rayyanhunerkar.todolist.model.Card;
import com.rayyanhunerkar.todolist.model.State;
import com.rayyanhunerkar.todolist.model.User;
import com.rayyanhunerkar.todolist.repository.CardRepository;
import com.rayyanhunerkar.todolist.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StateService {
    @Autowired
    private final StateRepository stateRepository;

    @Autowired
    private final CardRepository cardRepository;

    public StateService(StateRepository stateRepository, CardRepository cardRepository) {
        this.stateRepository = stateRepository;
        this.cardRepository = cardRepository;
    }

    public Response<Object> createState(final StateRequest stateRequest) throws Exception {

        State state;
        StateNoTasksResponse response;

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        state = stateRepository.save(State.builder()
                .name(stateRequest.getName())
                .description(stateRequest.getDescription())
                .team(user.getTeam())
                .createdOn(new Date())
                .modifiedOn(new Date())
                .build()
        );

        response = StateNoTasksResponse.builder()
                .id(state.getId())
                .name(state.getName())
                .description(state.getDescription())
                .createdOn(state.getCreatedOn())
                .modifiedOn(state.getModifiedOn())
                .build();

        return Response.builder()
                .data(response)
                .message("State created successfully")
                .build();
    }

    public Response<Object> getStates() throws Exception {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<State> states = stateRepository.findAllByTeamId(user.getTeam().getId());

        List<StateResponse> response = states.stream()
                .map(state -> {
                    List<Card> cards = cardRepository.findAllByStateId(state.getId());
                    List<CardResponse> cardResponse = cards.stream().map(
                        card -> CardResponse.builder()
                            .id(card.getId())
                            .title(card.getTitle())
                            .description(card.getDescription())
                            .deadline(card.getDeadline())
                            .createdBy(card.getCreatedBy().getId())
                            .createdOn(card.getCreatedOn())
                            .modifiedOn(card.getModifiedOn())
                            .build()

                    ).toList();

                    return StateResponse.builder()
                        .id(state.getId())
                        .name(state.getName())
                        .description(state.getDescription())
                        .tasks(cardResponse)
                        .createdOn(state.getCreatedOn())
                        .modifiedOn(state.getModifiedOn())
                        .build();
                        }
                ).toList();

        return Response.builder()
                .data(response)
                .message("States retrieved successfully")
                .build();
    }

    public Response<Object> getState(UUID id) throws Exception {
        Optional<State> state;
        List<Card> cards;

        state = stateRepository.findById(id);

        if (state.isEmpty()) {
            return null;
        }

        cards = cardRepository.findAllByStateId(id);

        List<CardResponse> cardResponse = cards.stream().map(
            card -> CardResponse.builder()
                .id(card.getId())
                .title(card.getTitle())
                .description(card.getDescription())
                .deadline(card.getDeadline())
                .createdBy(card.getCreatedBy().getId())
                .createdOn(card.getCreatedOn())
                .modifiedOn(card.getModifiedOn())
                .build()
        ).toList();

        State stateEntity = state.get();

        StateResponse response = StateResponse.builder()
                .id(stateEntity.getId())
                .name(stateEntity.getName())
                .description(stateEntity.getDescription())
                .tasks(cardResponse)
                .createdOn(stateEntity.getCreatedOn())
                .modifiedOn(stateEntity.getModifiedOn())
                .build();

        return Response.builder()
                .data(response)
                .message("State received successfully!")
                .build();
    }

}
