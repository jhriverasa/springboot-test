package com.springapitest.restapi.repositories;

import org.springframework.data.repository.CrudRepository;
import com.springapitest.restapi.models.Transaction;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    public List<Transaction> getTransactionByTransactionId(String transactionId);
}