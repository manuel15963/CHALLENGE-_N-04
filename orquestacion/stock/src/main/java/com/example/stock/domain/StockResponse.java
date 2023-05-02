package com.example.stock.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class StockResponse {

    private String id;
    private String idProduct;
    private Integer stock;

}
