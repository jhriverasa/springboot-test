package com.springapitest.restapi.models;

import com.springapitest.restapi.utility.AbstractEntityTimeStamps;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity //Make a table out of this class
public class Transaction extends AbstractEntityTimeStamps {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private Long transactionId;

    // fk
    @ManyToOne
    @JoinColumn(name = "cardId")
    private Card card;

    @Column(columnDefinition = "Decimal(12,2)", nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;

    @Column(nullable = false)
    private String status;



}
