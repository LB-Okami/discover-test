package com.discover.discover.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.discover.discover.models.Card;
import com.discover.discover.models.Client;
import com.discover.discover.repositories.CardRepository;
import com.discover.discover.services.CardService;

@RestController
@RequestMapping("/cards")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CardController {
    
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardService cardService;

    @GetMapping
	public ResponseEntity<List<Card>> FindAll() {
		return ResponseEntity.ok(cardRepository.findAll());
	}

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable long id){
        return cardRepository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
    }

   /*  @PostMapping
    public ResponseEntity<Card> save(@RequestBody Card card) {
        Optional<Card> optionalCard = cardService.createCard(card);
		try {
			return ResponseEntity.ok(optionalCard.get());
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
    }*/

    @PutMapping(path = {"/put/{id}"})
	public ResponseEntity<Card> useLimit(@RequestBody Card card, @PathVariable Long id) {
        
        Optional<Card> optionalCard = cardService.transaction(card);
		try {
			return ResponseEntity.ok(optionalCard.get());
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}


    

}
