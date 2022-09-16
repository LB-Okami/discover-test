package com.discover.discover.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.discover.discover.models.Client;
import com.discover.discover.repositories.ClientRepository;
import com.discover.discover.services.CardService;
import com.discover.discover.services.ClientService;

@RestController
@RequestMapping("/clients")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClientController {
    
    @Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ClientService clientService;

	@Autowired
	private CardService cardService;

    @GetMapping
	public ResponseEntity<List<Client>> FindAll() {
		return ResponseEntity.ok(clientRepository.findAll());
	}

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable long id){
        return clientRepository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Client> save(@RequestBody Client client) {

		cardService.createCard(client.getCard(), client);
        Optional<Client> optionalClient = clientService.saveClient(client);
		try {
			return ResponseEntity.ok(optionalClient.get());
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
        
    }

    @PutMapping(path = {"/put/{id}"})
	public ResponseEntity<Client> put(@RequestBody Client client, @PathVariable Long id) {
        
        Optional<Client> optionalClient = clientService.saveClient(client);
		try {
			return ResponseEntity.ok(optionalClient.get());
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

    @DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		clientRepository.deleteById(id);
	}

}
