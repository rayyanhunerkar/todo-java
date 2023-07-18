package com.rayyanhunerkar.todolist.POJO.Users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpResponse {
    private UUID id;
    private String email;
    private String firstName;
    private String lastName;
}
