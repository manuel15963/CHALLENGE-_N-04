package com.ms.vallegrande.order.domain;


import lombok.Builder;
import lombok.Data;

@Data
public class Stock {

    private String id;
    private String idProduct;
    private Integer stock;


}


