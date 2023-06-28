package com.rayyanhunerkar.todolist.controller;

import com.rayyanhunerkar.todolist.POJO.Response.Response;
import com.rayyanhunerkar.todolist.POJO.State.StateRequest;
import com.rayyanhunerkar.todolist.POJO.State.StateResponse;
import com.rayyanhunerkar.todolist.service.StateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StateController {
    private final StateService stateService;

    public StateController(StateService stateService) {
        this.stateService = stateService;
    }

    @PostMapping("/states")
    public ResponseEntity<Response> createState(@RequestBody StateRequest request) throws Exception {
        return new ResponseEntity<>(stateService.createState(request), HttpStatus.OK);
    }
}
