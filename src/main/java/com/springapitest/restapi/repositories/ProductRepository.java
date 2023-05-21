package com.springapitest.restapi.repositories;
import org.springframework.data.repository.CrudRepository;
import com.springapitest.restapi.models.Product;

import java.util.List;


public interface ProductRepository extends CrudRepository<Product, Long> {
    public List<Product> findByProductId(Long productId);
}
