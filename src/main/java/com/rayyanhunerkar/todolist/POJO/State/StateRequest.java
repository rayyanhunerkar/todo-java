package com.rayyanhunerkar.todolist.POJO.State;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StateRequest {


    @NotNull
    @NotBlank
    private String name;
    private String description;
}
