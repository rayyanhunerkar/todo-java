package com.rayyanhunerkar.todolist.controller;

import com.rayyanhunerkar.todolist.POJO.Teams.TeamAddUsersRequest;
import com.rayyanhunerkar.todolist.POJO.Teams.TeamRequest;
import com.rayyanhunerkar.todolist.service.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin
public class TeamController {
    @Autowired
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping("/teams")
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    public ResponseEntity<Object> createTeam(@RequestBody @NotNull TeamRequest request) throws Exception {
        return new ResponseEntity<>(teamService.createTeam(request), HttpStatus.CREATED);
    }

    @GetMapping("/teams")
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    public ResponseEntity<Object> getTeams() throws Exception {
        return new ResponseEntity<>(teamService.getTeams(), HttpStatus.OK);
    }

    @PostMapping("/teams/{id}")
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    public ResponseEntity<Object> addTeamMembers(@PathVariable("id") @NotNull String id, @RequestBody @NotNull TeamAddUsersRequest request) throws Exception {
        return new ResponseEntity<>(teamService.addUsersToTeam(UUID.fromString(id), request), HttpStatus.OK);
    }
}

