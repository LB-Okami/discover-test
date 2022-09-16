package com.discover.discover.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.discover.discover.models.Client;
import com.discover.discover.repositories.ClientRepository;

@Service
public class ClientService {
    
    @Autowired
	private ClientRepository clientRepository;

    public Optional<Client> saveClient(Client client) {

        if(client.getId() == null && clientRepository.findByCpf(client.getCpf()) != null)
			return null;

        return Optional.of(clientRepository.save(client));
    }
    
}
