package com.springapitest.restapi.utility.request.bodies;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class CardBalanceBody {

    @Valid
    @NotBlank(message = "cardId field is mandatory")
    private String cardId;

    @NotBlank(message = "balance field is mandatory")
    @Positive(message = "the balance quantity should be positive")
    private BigDecimal balance;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
