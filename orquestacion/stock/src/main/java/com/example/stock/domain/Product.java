package com.example.stock.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Product {
    private String id;
    private String name;
    private Integer stock;
    private BigDecimal price;
}
