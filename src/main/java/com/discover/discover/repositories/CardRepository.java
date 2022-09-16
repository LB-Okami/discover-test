package com.discover.discover.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.discover.discover.models.Card;
import com.discover.discover.models.Client;

public interface CardRepository extends JpaRepository<Card, Long>{

    List<Card> findAll();

    Optional<Card> findById(Long id);
    
}
