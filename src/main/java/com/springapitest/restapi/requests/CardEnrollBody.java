package com.springapitest.restapi.requests;

import javax.validation.constraints.NotBlank;

public class CardEnrollBody {

    @NotBlank(message = "cardId field is mandatory")
    private String cardId;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
}
