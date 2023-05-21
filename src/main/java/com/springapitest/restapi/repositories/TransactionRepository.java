package com.springapitest.restapi.repositories;
import org.springframework.data.repository.CrudRepository;
import com.springapitest.restapi.models.Transaction;


public interface TransactionRepository extends CrudRepository<Transaction, Long> {

}