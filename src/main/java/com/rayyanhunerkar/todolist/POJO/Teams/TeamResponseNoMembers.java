package com.rayyanhunerkar.todolist.POJO.Teams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamResponseNoMembers {
    private UUID id;
    private String name;
    private Date createdOn;
    private Date modifiedOn;
}
