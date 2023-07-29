package com.rayyanhunerkar.todolist.service;

import com.rayyanhunerkar.todolist.POJO.Response.Response;
import com.rayyanhunerkar.todolist.POJO.Teams.TeamAddUsersRequest;
import com.rayyanhunerkar.todolist.POJO.Teams.TeamRequest;
import com.rayyanhunerkar.todolist.POJO.Teams.TeamResponse;
import com.rayyanhunerkar.todolist.POJO.Teams.TeamResponseNoMembers;
import com.rayyanhunerkar.todolist.POJO.Users.UserBaseResponse;
import com.rayyanhunerkar.todolist.model.Team;
import com.rayyanhunerkar.todolist.model.User;
import com.rayyanhunerkar.todolist.repository.TeamRepository;
import com.rayyanhunerkar.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TeamService {
    @Autowired
    private final TeamRepository teamRepository;

    @Autowired
    private final UserRepository userRepository;

    public TeamService(TeamRepository teamRepository, UserRepository userRepository) {
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
    }

    public Response<Object> createTeam(TeamRequest request) throws Exception {
        Team team;
        try {
            team = teamRepository.save(Team.builder()
                    .name(request.getName())
                    .modifiedOn(new Timestamp(new Date().getTime()))
                    .createdOn(new Date())
                    .build());
        } catch (Exception e) {
            throw new Exception(e);
        }

        TeamResponseNoMembers response = TeamResponseNoMembers.builder()
                .id(team.getId())
                .name(team.getName())
                .createdOn(team.getCreatedOn())
                .modifiedOn(team.getModifiedOn())
                .build();

        return Response.builder()
                .data(response)
                .message("Team created Successfully")
                .build();
    }

    public Response<Object> getTeams() throws Exception {
        List<Team> teams = teamRepository.findAll();

        List<TeamResponseNoMembers> response = teams.stream().map(
                team ->
                        TeamResponseNoMembers.builder()
                                .id(team.getId())
                                .name(team.getName())
                                .createdOn(team.getCreatedOn())
                                .modifiedOn(team.getModifiedOn())
                                .build()
        ).toList();

        return Response.builder()
                .data(response)
                .message("Teams fetched successfully!")
                .build();
    }

    public Response<Object> addUsersToTeam(UUID id, TeamAddUsersRequest request) throws Exception {


        Optional<Team> team = teamRepository.findById(id);
        if (team.isEmpty()) {
            return null;
        }

        List<User> users = userRepository.findByIdIn(request.getUsers());

        users = users.stream().map(
                user -> {
                    user.setTeam_id(team.get());
                    return userRepository.save(user);
                }
        ).toList();

        List<UserBaseResponse> userBaseResponses = users.stream().map(
                user ->
                        UserBaseResponse.builder()
                                .id(user.getId())
                                .email(user.getEmail())
                                .firstName(user.getFirstName())
                                .lastName(user.getLastName())
                                .build()
        ).toList();

        TeamResponse teamResponse = TeamResponse.builder()
                .id(team.get().getId())
                .name(team.get().getName())
                .users(userBaseResponses)
                .createdOn(team.get().getCreatedOn())
                .modifiedOn(team.get().getModifiedOn())
                .build();

        return Response.builder()
                .data(teamResponse)
                .message("Users added successfully")
                .build();
    }
}
