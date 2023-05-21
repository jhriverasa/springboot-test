package com.springapitest.restapi.models;

import com.springapitest.restapi.utility.AbstractEntityTimeStamps;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity //Make a table out of this class
public class Transaction extends AbstractEntityTimeStamps {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @Column(unique = true, nullable = false)
    private Integer transactionId;

    // fk
    @ManyToOne
    @JoinColumn(name = "cardId")
    private Card card;
    @Column(columnDefinition = "Decimal(12,2)", nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;
    @Column(nullable = false)
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
