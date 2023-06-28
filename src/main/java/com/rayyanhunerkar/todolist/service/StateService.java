package com.rayyanhunerkar.todolist.service;

import com.rayyanhunerkar.todolist.POJO.Response.Response;
import com.rayyanhunerkar.todolist.POJO.State.StateRequest;
import com.rayyanhunerkar.todolist.POJO.State.StateResponse;
import com.rayyanhunerkar.todolist.model.State;
import com.rayyanhunerkar.todolist.repository.CardRepository;
import com.rayyanhunerkar.todolist.repository.StateRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StateService {
    private final StateRepository stateRepository;

    public StateService(CardRepository cardRepository, StateRepository stateRepository) {
        this.stateRepository = stateRepository;
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
        List<State> states = stateRepository.findAll();

        List<StateResponse> response = states.stream()
                .map(
                        state -> {
                            return StateResponse.builder()
                                    .id(state.getId())
                                    .name(state.getName())
                                    .description(state.getDescription())
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

}
