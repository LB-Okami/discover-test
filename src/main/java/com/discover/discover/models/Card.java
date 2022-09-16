package com.discover.discover.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_card")
public class Card {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long id;

    @NotNull
    @Size(min = 4)
    private String password;

    private Double cardLimit;

    private Double transaction;

    private LocalDate creationDate;

    private LocalDateTime lastTransaction;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getCardLimit() {
        return cardLimit;
    }

    public void setCardLimit(Double cardLimit) {
        this.cardLimit = cardLimit;
    }

    public Double getTransaction() {
        return transaction;
    }

    public void setTransaction(Double transaction) {
        this.transaction = transaction;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getLastTransaction() {
        return lastTransaction;
    }

    public void setLastTransaction(LocalDateTime lastTransaction) {
        this.lastTransaction = lastTransaction;
    }

    

    /*@OneToOne(mappedBy = "card")
    private Client client;*/

    

    /*public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
*/
    

}
