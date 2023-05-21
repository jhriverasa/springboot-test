package com.springapitest.restapi.repositories;
import org.springframework.data.repository.CrudRepository;
import com.springapitest.restapi.models.Card;

import java.util.List;


public interface CardRepository extends CrudRepository<Card, Long> {
    public List<Card> getCardByCardId(String cardId);
}