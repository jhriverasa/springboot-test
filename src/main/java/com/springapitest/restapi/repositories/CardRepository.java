package com.springapitest.restapi.repositories;
import org.springframework.data.repository.CrudRepository;
import com.springapitest.restapi.models.Card;


public interface CardRepository extends CrudRepository<Card, Long> {
}