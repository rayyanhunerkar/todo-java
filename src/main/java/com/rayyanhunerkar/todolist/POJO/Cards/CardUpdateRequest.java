package com.rayyanhunerkar.todolist.POJO.Cards;

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
public class CardUpdateRequest {
    private String title;
    private String description;
    private Date deadline;
    private UUID state;
}
