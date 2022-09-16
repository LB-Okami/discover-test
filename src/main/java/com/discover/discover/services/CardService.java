package com.discover.discover.services;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.discover.discover.models.Card;
import com.discover.discover.models.Client;
import com.discover.discover.repositories.CardRepository;
import com.discover.discover.repositories.ClientRepository;

@Service
public class CardService {
    
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ClientRepository clientRepository;

    public void createCard(Card card, Client client) {

        //if(cardRepository.findByCpf(client.getCpf()) != null)
		//	return null;

        card.setCreationDate(LocalDate.now());

        if(client.getSalary() * 0.3 > 2000) {
            card.setCardLimit(Double.valueOf(2000));
        }
        else if(client.getSalary() * 0.3 < 300) {
            card.setCardLimit(Double.valueOf(300));
        }
        else {
            card.setCardLimit(client.getSalary() * 0.3);
        }

    }

    public Optional<Card> transaction(Card card) {

        Card databaseCard = cardRepository.findById(card.getId()).get();

        if(card.getPassword().equals(databaseCard.getPassword())) {
            if(card.getCardLimit() >= card.getTransaction()) {
                card.setCardLimit(card.getCardLimit() - card.getTransaction());
            }
            else {
                return null;
            }
        }
        else {
            return null;
        }

        card.setTransaction(null);

        return Optional.of(cardRepository.save(card));

    }

}
