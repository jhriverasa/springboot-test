package com.springapitest.restapi.controllers;

import com.springapitest.restapi.utility.Utility;
import com.springapitest.restapi.utility.request.bodies.CardBalanceBody;
import com.springapitest.restapi.utility.request.bodies.CardEnrollBody;
import com.springapitest.restapi.utility.response.types.StringResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springapitest.restapi.models.Card;
import com.springapitest.restapi.repositories.CardRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/") // URL's starts with /

public class CardController {

    @Autowired
    private CardRepository cardRepository;

    // 1. 1) Generar numero de tarjeta
    @GetMapping(path = "/card/{productId}/number")
    public ResponseEntity<Object> generateCardNumber(@PathVariable("productId") String productId) {
        try {
            String idFirstPart = productId;

            if (!idFirstPart.matches("[0-9]{6}")) {
                throw new RuntimeException("invalid productId");
            }

            // generates the id given the specifications (6digits from productid + 10 random
            // digits)
            String idSecondPart = Utility.generateStringDigits(10);
            String completeId = idFirstPart + idSecondPart;

            Card cardToSave = new Card();

            // Creates the new card
            cardToSave.setCardId(completeId);
            cardToSave.setProductId(productId);

            cardToSave.setHolder("Jhonatan Rivera");
            cardToSave.setType("Debito");
            cardToSave.setExpiration(LocalDate.now().plusYears(3));
            cardToSave.setActive(false);
            cardToSave.setBalance(new BigDecimal(0.00));

            cardRepository.save(cardToSave);

            // responds with the Id
            return new ResponseEntity<>(cardToSave, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println("Something went wrong.");
            if (e.getMessage().indexOf("Duplicate entry") != -1) {
                // sends a default message so that it avoids sending sensible data
                return new ResponseEntity<>(new StringResponse("Error: an error ocurred, try again."),
                        HttpStatus.BAD_REQUEST);
            }
            System.out.println("Error message: " + e.getMessage());
            return new ResponseEntity<>(new StringResponse("Error: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    // 1. 2) Activar tarjeta
    @PostMapping(path = "/card/enroll", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> activateCard(@Valid @RequestBody CardEnrollBody bodyObj) {
        try {
            String cardId = bodyObj.getCardId();
            // evaluates a reg.expression for strings of 10 digits
            if (cardId == null || !cardId.matches("[0-9]{16}")) {
                throw new RuntimeException("Invalid body for this request");
            }

            List<Card> cards = cardRepository.getCardByCardId(cardId);
            if (cards.size() != 1) {
                throw new RuntimeException("Invalid cardId");
            }

            Card cardToSave = cards.get(0);
            cardToSave.setActive(true);
            cardRepository.save(cardToSave);

            return new ResponseEntity<>(bodyObj, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
            return new ResponseEntity<>(new StringResponse("Error: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }

    }

    // 1. 3) Bloquear tarjeta
    @DeleteMapping(path = "/card/{cardId}")
    public ResponseEntity<Object> lockCard(@PathVariable("cardId") String cardId) {
        try {
            // evaluates a reg.expression for strings of 10 digits
            if (!cardId.matches("[0-9]{16}")) {
                throw new RuntimeException("Invalid cardId.");
            }

            List<Card> cards = cardRepository.getCardByCardId(cardId);
            if (cards.size() != 1) {
                throw new RuntimeException("Invalid cardId");
            }

            Card cardToSave = cards.get(0);
            cardRepository.delete(cardToSave);
            return new ResponseEntity<>(new StringResponse("Card locked."), HttpStatus.OK);

        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
            return new ResponseEntity<>(new StringResponse("Error: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }

    }

    // 1. 4) Recargar tarjeta
    @PostMapping(path = "/card/balance", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addBalance(@Valid @RequestBody CardBalanceBody body) {
        try {
            String cardId = body.getCardId();
            BigDecimal balanceToAdd = body.getBalance();
            // evaluates a reg.expression for strings of 10 digits
            if (cardId == null || !cardId.matches("[0-9]{16}")) {
                throw new RuntimeException("Invalid body for this request");
            }

            List<Card> cards = cardRepository.getCardByCardId(cardId);
            if (cards.size() != 1) {
                throw new RuntimeException("Invalid cardId");
            }

            Card cardToSave = cards.get(0);
            if (!cardToSave.getActive()) {
                throw new RuntimeException("Inactive card.");
            }

            BigDecimal curBalance = cardToSave.getBalance();
            BigDecimal newBalance = curBalance.add(balanceToAdd);
            cardToSave.setBalance(curBalance.add(balanceToAdd));
            cardRepository.save(cardToSave);
            body.setBalance(newBalance);

            return new ResponseEntity<>(body, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
            return new ResponseEntity<>(new StringResponse("Error: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }

    }

    // 1. 5) Consulta de saldo
    @GetMapping(path = "/card/balance/{cardId}")
    public ResponseEntity<Object> getCardBalance(@PathVariable("cardId") String cardId) {
        try {
            // evaluates a reg.expression for strings of 10 digits
            if (!cardId.matches("[0-9]{16}")) {
                throw new RuntimeException("Invalid body for this request");
            }
            List<Card> cards = cardRepository.getCardByCardId(cardId);
            if (cards.size() != 1) {
                throw new RuntimeException("Invalid cardId");
            }
            Card requestedCard = cards.get(0);
            if (!requestedCard.getActive()) {
                throw new RuntimeException("Inactive card.");
            }
            CardBalanceBody res = new CardBalanceBody();
            res.setBalance(requestedCard.getBalance());
            res.setCardId(cardId);

            return new ResponseEntity<>(res, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println("Something went wrong.");
            if (e.getMessage().indexOf("Duplicate entry") != -1) {
                // sends a default message so that it avoids sending sensible data
                return new ResponseEntity<>(new StringResponse("Error: an error ocurred, try again."),
                        HttpStatus.BAD_REQUEST);
            }
            System.out.println("Error message: " + e.getMessage());
            return new ResponseEntity<>(new StringResponse("Error: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
