package com.ms.pedido.vallegrande.infraestructure.repository.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;


@Document(collection = "product")
@Data
public class Product {

    @Id
    private String id;
    private String name;
    private BigDecimal price;

}
