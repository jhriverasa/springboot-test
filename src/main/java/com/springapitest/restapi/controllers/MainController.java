package com.springapitest.restapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springapitest.restapi.models.Card;
import com.springapitest.restapi.repositories.CardRepository;

import com.springapitest.restapi.models.Product;
import com.springapitest.restapi.repositories.ProductRepository;

import com.springapitest.restapi.models.Transaction;
import com.springapitest.restapi.repositories.TransactionRepository;

@Controller // Defines a Controller
@RequestMapping(path = "/") // URL's starts with / (after Application path)

public class MainController {
    
    // This means to get the bean called xRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    @Autowired 
    private CardRepository cardRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    /*
     * @PostMapping(path="/add") // Map ONLY POST Requests
     * public @ResponseBody String addNewCard (@RequestParam String cardId,
     * 
     * @RequestParam String holder) {
     * // @ResponseBody means the returned String is the response, not a view name
     * // @RequestParam means it is a parameter from the GET or POST request
     * 
     * Card c = new Card();
     * c.setName(name);
     * n.setEmail(email);
     * userRepository.save(n);
     * return "Saved";
     * }
     */

    @GetMapping(path = "/cards")
    public @ResponseBody Iterable<Card> getAllCards() {
        // This returns a JSON or XML
        return cardRepository.findAll();
    }

    @GetMapping(path = "/products")
    public @ResponseBody Iterable<Product> getAllProducts() {
        // This returns a JSON or XML
        return productRepository.findAll();
    }

    @GetMapping(path = "/transactions")
    public @ResponseBody Iterable<Transaction> getAllTransactions() {
        // This returns a JSON or XML
        return transactionRepository.findAll();
    }
}
