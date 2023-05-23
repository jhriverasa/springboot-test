package com.springapitest.restapi.controllers;

import com.springapitest.restapi.models.Transaction;
import com.springapitest.restapi.repositories.TransactionRepository;

import com.springapitest.restapi.requests.CardBalanceBody;
import com.springapitest.restapi.requests.TransactionCancellingBody;
import com.springapitest.restapi.requests.TransactionPurchaseBody;
import com.springapitest.restapi.responses.StringResponse;
import com.springapitest.restapi.responses.TransactionResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springapitest.restapi.models.Card;
import com.springapitest.restapi.repositories.CardRepository;

import java.math.BigDecimal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static com.springapitest.restapi.utility.Utility.generateStringDigits;

@RestController
@RequestMapping(path = "/") // URL's starts with /

public class TransactionController {
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    // 1. 6) Transacción de compra
    @PostMapping(path = "/transaction/purchase", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> transactionPurchase(@Valid @RequestBody TransactionPurchaseBody body) {
        try {

            String cardId = body.getCardId();
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
            BigDecimal price = body.getPrice();
            BigDecimal newBalance = curBalance.subtract(price);

            // checks if val is negative
            if (newBalance.compareTo(BigDecimal.ZERO) == -1) {
                throw new RuntimeException("Card balance is insufficient.");
            }

            cardToSave.setBalance(newBalance);
            cardRepository.save(cardToSave);

            Transaction transactionToSave = new Transaction();
            transactionToSave.setPrice(body.getPrice());
            transactionToSave.setTransactionId(generateStringDigits(16));
            transactionToSave.setCard(cardToSave);
            transactionToSave.setStatus(Transaction.STATUS_COMPLETED);
            transactionRepository.save(transactionToSave);

            CardBalanceBody res = new CardBalanceBody();
            res.setCardId(cardId);
            res.setBalance(newBalance);

            return new ResponseEntity<>(res, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
            return new ResponseEntity<>(new StringResponse("Error: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }

    }

    // 1. 7) Consultar transacción
    @GetMapping(path = "/transaction/{transactionId}")
    public ResponseEntity<Object> getTransaction(@PathVariable("transactionId") String transactionId) {
        try {

            if (transactionId.length() != 16 || !transactionId.matches("[0-9]{16}")) {
                throw new RuntimeException("Invalid request");
            }
            List<Transaction> transactions = transactionRepository.getTransactionByTransactionId(transactionId);

            if (transactions.size() != 1) {
                throw new RuntimeException("Invalid transactionId");
            }

            TransactionResponse res = new TransactionResponse();
            res.setTransactionId(transactions.get(0).getTransactionId());
            res.setPrice(transactions.get(0).getPrice());
            res.setStatus(transactions.get(0).getStatus());

            return new ResponseEntity<>(res, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println("Something went wrong.");
            System.out.println("Error message: " + e.getMessage());
            return new ResponseEntity<>(new StringResponse("Error: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    // 2. 1) Anular transaccion
    @PostMapping(path = "/transaction/anulation", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> transactionCancelling(@Valid @RequestBody TransactionCancellingBody body) {
        try {

            String transactionId = body.getTransactionId();
            String cardId = body.getCardId();
            // evaluates a reg.expression for strings of 16 digits
            if (transactionId == null || !transactionId.matches("[0-9]{16}") || cardId == null
                    || !cardId.matches("[0-9]{16}")) {
                throw new RuntimeException("Invalid body for this request");
            }

            List<Card> cards = cardRepository.getCardByCardId(cardId);
            List<Transaction> transacts = transactionRepository.getTransactionByTransactionId(transactionId);
            if (cards.size() != 1 || transacts.size() != 1) {
                throw new RuntimeException("Invalid Ids");
            }

            Card cardToSave = cards.get(0);
            if (!cardToSave.getActive()) {
                throw new RuntimeException("Inactive card.");
            }

            Transaction transactionToSave = transacts.get(0);
            // verifies that cardId from transaction corresponds to cardId
            // in other words that transactions was made using this specific card
            if (!Objects.equals(transactionToSave.getCard().getCardId(), cardToSave.getCardId())) {
                throw new RuntimeException("Invalid Ids");
            }

            if (Objects.equals(transactionToSave.getStatus(), Transaction.STATUS_CANCELLED)) {
                throw new RuntimeException("Transaction already canceled.");
            }
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime oneDayAfterCreation = (transactionToSave.getCreatedAt()).plusDays(1l);
            if (oneDayAfterCreation.compareTo(now) < 0) {
                throw new RuntimeException(
                        "More than a Day has passed. This transaction can no longer be cancelled.");
            }

            BigDecimal curBalance = cardToSave.getBalance();
            BigDecimal additionalBalance = transactionToSave.getPrice();
            BigDecimal newBalance = curBalance.add(additionalBalance);

            cardToSave.setBalance(newBalance);
            transactionToSave.setStatus(Transaction.STATUS_CANCELLED);
            transactionRepository.save(transactionToSave);
            cardRepository.save(cardToSave);

            CardBalanceBody res = new CardBalanceBody();
            res.setCardId(cardId);
            res.setBalance(newBalance);

            return new ResponseEntity<>(res, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
            return new ResponseEntity<>(new StringResponse("Error: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }

    }

}
