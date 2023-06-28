package com.rayyanhunerkar.todolist.POJO.Cards;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardRequest {

    @NotNull(message = "Title can not be null")
    @NotBlank
    private String title;
    private String description;
    private Date deadline;
    @NotNull
    private UUID state_id;
}
