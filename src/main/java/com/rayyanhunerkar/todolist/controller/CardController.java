package com.rayyanhunerkar.todolist.controller;

import com.rayyanhunerkar.todolist.POJO.Cards.CardRequest;
import com.rayyanhunerkar.todolist.POJO.Cards.CardUpdateRequest;
import com.rayyanhunerkar.todolist.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin
public class CardController {
    @Autowired
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    @PostMapping("/cards")
    public ResponseEntity<Object> createCard(@RequestBody final CardRequest cardRequest) throws Exception {
        return new ResponseEntity<>(cardService.createCard(cardRequest), HttpStatus.OK);
    }

    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    @GetMapping("/cards")
    public ResponseEntity<Object> getCards() throws Exception {
        return new ResponseEntity<>(cardService.getCards(), HttpStatus.OK);
    }

    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    @GetMapping("/cards/{id}")
    public ResponseEntity<Object> getCard(@RequestParam(value = "id") @NotNull String id) throws Exception {
        return new ResponseEntity<>(cardService.getCard(UUID.fromString(id)), HttpStatus.OK);
    }

    @PatchMapping("/cards/{id}")
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    public ResponseEntity<Object> updateCard(@RequestParam(value = "id") @NotNull String id, @RequestBody final CardUpdateRequest request) throws Exception {
        return new ResponseEntity<>(cardService.updateCard(UUID.fromString(id), request), HttpStatus.OK);
    }

}
