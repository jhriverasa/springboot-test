package com.springapitest.restapi.models;

import com.springapitest.restapi.utility.AbstractEntityTimeStamps;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity // Make a table out of this class
public class Card extends AbstractEntityTimeStamps {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(unique = true, nullable = false)
    private Integer cardId;

    // fk
    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    @Column(nullable = false)
    private String holder;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Date expiration;

    @Column(columnDefinition = "boolean default false", nullable = false)
    private Boolean active;
    
    @Column(columnDefinition = "Decimal(12,2) default '0.00'", nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
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

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
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
