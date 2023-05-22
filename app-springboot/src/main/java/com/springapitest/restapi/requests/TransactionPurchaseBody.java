package com.springapitest.restapi.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import java.math.BigDecimal;

public class TransactionPurchaseBody {

    @NotBlank(message = "cardId field is mandatory")
    private String cardId;

    @NotBlank(message = "balance field is mandatory")
    @Positive(message = "the price quantity should be positive")
    private BigDecimal price;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
