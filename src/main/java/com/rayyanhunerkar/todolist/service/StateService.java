package com.rayyanhunerkar.todolist.service;

import com.rayyanhunerkar.todolist.POJO.Response.Response;
import com.rayyanhunerkar.todolist.POJO.State.StateRequest;
import com.rayyanhunerkar.todolist.POJO.State.StateResponse;
import com.rayyanhunerkar.todolist.model.State;
import com.rayyanhunerkar.todolist.repository.CardRepository;
import com.rayyanhunerkar.todolist.repository.StateRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class StateService {
    private final CardRepository cardRepository;
    private final StateRepository stateRepository;

    public StateService(CardRepository cardRepository, StateRepository stateRepository) {
        this.cardRepository = cardRepository;
        this.stateRepository = stateRepository;
    }

    public Response<Object> createState(final StateRequest stateRequest) throws Exception {
        State state;

        state = stateRepository.save(State.builder()
                .name(stateRequest.getName())
                .description(stateRequest.getDescription())
                .createdOn(new Date())
                .modifiedOn(new Date())
                .build()
        );

        StateResponse response = StateResponse.builder()
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

}
