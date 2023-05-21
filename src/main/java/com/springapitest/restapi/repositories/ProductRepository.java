package com.springapitest.restapi.repositories;

import org.springframework.data.repository.CrudRepository;
import com.springapitest.restapi.models.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
