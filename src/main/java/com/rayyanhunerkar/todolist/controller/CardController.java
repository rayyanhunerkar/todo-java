package com.rayyanhunerkar.todolist.controller;

import com.rayyanhunerkar.todolist.POJO.Cards.CardRequest;
import com.rayyanhunerkar.todolist.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class CardController {
    @Autowired
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/cards")
    public ResponseEntity<Object> createCard(@RequestBody final CardRequest cardRequest) throws Exception {
        return new ResponseEntity<>(cardService.createCard(cardRequest), HttpStatus.OK);
    }

    @GetMapping("/cards")
    public ResponseEntity<Object> getCards() throws Exception {
        return new ResponseEntity<>(cardService.getCards(), HttpStatus.OK);
    }
}
