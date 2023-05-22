package com.springapitest.restapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springapitest.restapi.utility.AbstractEntityTimeStamps;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity // Make a table out of this class
public class Card extends AbstractEntityTimeStamps {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String cardId;

    @Column(nullable = false)
    private String productId;

    @Column(nullable = false)
    private String holder;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private LocalDate expiration;

    @Column(columnDefinition = "boolean default false", nullable = false)
    private Boolean active;

    @Column(columnDefinition = "Decimal(12,2) default '0.00'", nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDate expiration) {
        this.expiration = expiration;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
