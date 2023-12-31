package com.rayyanhunerkar.todolist.POJO.State;

import com.rayyanhunerkar.todolist.POJO.Cards.CardResponse;
import com.rayyanhunerkar.todolist.model.Card;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StateResponse {

    private UUID id;
    private String name;
    private String description;
    private List<CardResponse> tasks;
    private Date createdOn;
    private Date modifiedOn;
}
