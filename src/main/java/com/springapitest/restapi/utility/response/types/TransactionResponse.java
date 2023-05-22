package com.springapitest.restapi.utility.response.types;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class TransactionResponse {
    @Valid
    @NotBlank(message = "cardId field is mandatory")
    private String transactionId;

    @NotBlank(message = "balance field is mandatory")
    @Positive(message = "the price quantity should be positive")
    private BigDecimal price;

    @NotBlank(message = "balance field is mandatory")
    private String status;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

