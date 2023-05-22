package com.springapitest.restapi.utility.request.bodies;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public class TransactionCancellingBody {
    @Valid
    @NotBlank(message = "cardId field is mandatory")
    private String cardId;
    @Valid
    @NotBlank(message = "transactionId field is mandatory")
    private String transactionId;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
