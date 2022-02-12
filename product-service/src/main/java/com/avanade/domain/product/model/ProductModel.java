package com.avanade.domain.product.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("Products")
public class ProductModel {

    @Id
    private String id;
    private String description;

}
