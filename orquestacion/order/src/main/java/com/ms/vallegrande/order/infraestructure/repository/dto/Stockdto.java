package com.ms.vallegrande.order.infraestructure.repository.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class Stockdto {

    private String idProduct;
    private Integer stock;
}
