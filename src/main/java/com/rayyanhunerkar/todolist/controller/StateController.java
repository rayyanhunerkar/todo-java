package com.rayyanhunerkar.todolist.controller;

import com.rayyanhunerkar.todolist.POJO.State.StateRequest;
import com.rayyanhunerkar.todolist.service.StateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@RestController
@CrossOrigin
public class StateController {
    @Autowired
    private final StateService stateService;

    public StateController(StateService stateService) {
        this.stateService = stateService;
    }

    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    @PostMapping("/states")
    public ResponseEntity<Object> createState(@RequestBody StateRequest request) throws Exception {
        return new ResponseEntity<>(stateService.createState(request), HttpStatus.OK);
    }

    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    @GetMapping("/states")
    public ResponseEntity<Object> getStates() throws Exception {
        return new ResponseEntity<>(stateService.getStates(), HttpStatus.OK);
    }

    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    @GetMapping("/states/{id}")
    public ResponseEntity<Object> getState(@PathVariable("id") @NotNull String id) throws Exception {
        return new ResponseEntity<>(stateService.getState(UUID.fromString(id)), HttpStatus.OK);
    }
}
