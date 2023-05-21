package com.springapitest.restapi.models;

import com.springapitest.restapi.utility.AbstractEntityTimeStamps;
import jakarta.persistence.*;

@Entity // Make a table out of this class
public class Product extends AbstractEntityTimeStamps {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(unique = true, nullable = false)
    private Integer productId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
