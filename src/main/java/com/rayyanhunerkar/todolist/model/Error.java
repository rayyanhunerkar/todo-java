package com.rayyanhunerkar.todolist.model;

import java.time.LocalDateTime;

public record Error(LocalDateTime timestamp, String message, String detail) {
}
