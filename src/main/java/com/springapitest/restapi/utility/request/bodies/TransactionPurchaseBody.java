package com.springapitest.restapi.utility.request.bodies;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class TransactionPurchaseBody {
    @Valid
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
