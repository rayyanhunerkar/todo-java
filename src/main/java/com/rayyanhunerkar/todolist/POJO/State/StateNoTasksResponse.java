package com.rayyanhunerkar.todolist.POJO.State;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StateNoTasksResponse {
    private UUID id;
    private String name;
    private String description;
    private Date createdOn;
    private Date modifiedOn;
}
