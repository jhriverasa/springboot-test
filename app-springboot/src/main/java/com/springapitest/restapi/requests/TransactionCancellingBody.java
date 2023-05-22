package com.springapitest.restapi.requests;


import javax.validation.constraints.NotBlank;

public class TransactionCancellingBody {

    @NotBlank(message = "cardId field is mandatory")
    private String cardId;

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
