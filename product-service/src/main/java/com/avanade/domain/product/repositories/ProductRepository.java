package com.avanade.domain.product.repositories;

import com.avanade.domain.product.model.ProductModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductRepository extends MongoRepository<ProductModel, String> {

    Optional<ProductModel> findByDescription(String description);

}
