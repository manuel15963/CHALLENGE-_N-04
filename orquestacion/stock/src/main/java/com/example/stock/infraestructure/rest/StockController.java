package com.example.stock.infraestructure.rest;

import com.example.stock.application.StockService;
import com.example.stock.domain.StockRequest;
import com.example.stock.domain.StockResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    StockService stockService;

    @GetMapping("/listado")
    public List<StockResponse> finAll(){
        return stockService.finAll();
    }


    @PostMapping
    public StockResponse save(@RequestBody StockRequest stockRequest){
        return stockService.create(stockRequest);
    }

    @PutMapping()
    public ResponseEntity<?> update(@RequestBody StockRequest stockRequest){
        return stockService.update(stockRequest);
    }
}
