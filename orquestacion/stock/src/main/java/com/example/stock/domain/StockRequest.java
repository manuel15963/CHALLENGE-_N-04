package com.example.stock.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class StockRequest {

    private String idProduct;
    private Integer stock;

}
