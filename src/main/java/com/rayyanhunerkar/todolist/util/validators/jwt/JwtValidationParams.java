package com.rayyanhunerkar.todolist.util.validators.jwt;

import com.rayyanhunerkar.todolist.model.User;

public record JwtValidationParams(String token, User user) {}
