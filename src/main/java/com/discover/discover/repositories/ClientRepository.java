package com.discover.discover.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.discover.discover.models.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
    
    List<Client> findAll();

    Optional<Client> findById(Long id);

    Client findByCpf(String cpf);

    List<Client> findByName(String name);

}
