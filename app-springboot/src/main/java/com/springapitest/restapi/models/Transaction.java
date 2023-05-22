package com.springapitest.restapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springapitest.restapi.utility.AbstractEntityTimeStamps;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity // Make a table out of this class
public class Transaction extends AbstractEntityTimeStamps {
    public static String STATUS_COMPLETED = "completed";
    public static String STATUS_CANCELLED = "cancelled";

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String transactionId;

    // fk
    @ManyToOne
    @JoinColumn(name = "cardId")
    private Card card;

    @Column(columnDefinition = "Decimal(12,2)", nullable = false)
    private BigDecimal price = BigDecimal.ZERO;

    @Column(nullable = false)
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal balance) {
        this.price = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
