package com.rayyanhunerkar.todolist.POJO.Response;

import com.rayyanhunerkar.todolist.POJO.Cards.CardResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Response<T> {
    private T data;
    private String message;
}
