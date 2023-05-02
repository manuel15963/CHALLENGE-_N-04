package com.example.stock.infraestructure.repository.dto;


import com.example.stock.domain.Product;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "stock")
@Data
public class Stock {
    @Id
    private String id;
    private String idProduct;
    private Integer stock;

}
