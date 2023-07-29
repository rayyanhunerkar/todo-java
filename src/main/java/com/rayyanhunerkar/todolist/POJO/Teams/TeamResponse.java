package com.rayyanhunerkar.todolist.POJO.Teams;

import com.rayyanhunerkar.todolist.POJO.Users.UserBaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamResponse {
    private UUID id;
    private String name;
    private List<UserBaseResponse> users;
    private Date createdOn;
    private Date modifiedOn;
}
