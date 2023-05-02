package com.ms.vallegrande.order.infraestructure.feign;

import com.ms.vallegrande.order.infraestructure.repository.dto.Stockdto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "stock-service")
public interface StockClientFeign {
    @PutMapping(value = "/stock", consumes = MediaType.APPLICATION_JSON_VALUE)
    void update(@RequestBody Stockdto stockdto);


}



