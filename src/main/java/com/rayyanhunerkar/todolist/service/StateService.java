package com.rayyanhunerkar.todolist.service;

import com.rayyanhunerkar.todolist.POJO.Response.Response;
import com.rayyanhunerkar.todolist.POJO.State.StateRequest;
import com.rayyanhunerkar.todolist.POJO.State.StateResponse;
import com.rayyanhunerkar.todolist.model.Card;
import com.rayyanhunerkar.todolist.model.State;
import com.rayyanhunerkar.todolist.repository.CardRepository;
import com.rayyanhunerkar.todolist.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        StateResponse response;

        state = stateRepository.save(State.builder()
                .name(stateRequest.getName())
                .description(stateRequest.getDescription())
                .createdOn(new Date())
                .modifiedOn(new Date())
                .build()
        );

        response = StateResponse.builder()
                .id(state.getId())
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
        List<Card> cards;

        List<State> states = stateRepository.findAll();

        List<StateResponse> response = states.stream()
                .map(
                        state -> StateResponse.builder()
                                .id(state.getId())
                                .name(state.getName())
                                .description(state.getDescription())
                                .tasks(cardRepository.findAllByStateId(state.getId()))
                                .createdOn(state.getCreatedOn())
                                .modifiedOn(state.getModifiedOn())
                                .build()
                ).toList();

        return Response.builder()
                .data(response)
                .message("States retrieved successfully")
                .build();
    }

    public Response<Object> getState(UUID id) throws Exception {
        Optional<State> state;
        List<Card> card;

        state = stateRepository.findById(id);
        if (state.isEmpty()) {
            return null;
        }
        card = cardRepository.findAllByStateId(id);
        State stateEntity = state.get();

        StateResponse response = StateResponse.builder()
                .id(stateEntity.getId())
                .name(stateEntity.getName())
                .description(stateEntity.getDescription())
                .tasks(card)
                .createdOn(stateEntity.getCreatedOn())
                .modifiedOn(stateEntity.getModifiedOn())
                .build();

        return Response.builder()
                .data(response)
                .message("State received successfully!")
                .build();
    }

}
