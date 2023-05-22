package com.springapitest.restapi.utility.request.bodies;

import jakarta.validation.*;
import jakarta.validation.constraints.NotBlank;

public class CardEnrollBody {
    @Valid
    @NotBlank(message = "cardId field is mandatory")
    private String cardId;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
}
