package com.rayyanhunerkar.todolist.POJO.State;

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
public class StateResponse {

    private UUID id;
    private String name;
    private String description;
    private Date createdOn;
    private Date modifiedOn;

}
