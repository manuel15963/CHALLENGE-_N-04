package com.example.stock.application;

import com.example.stock.domain.StockRequest;
import com.example.stock.domain.StockResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface StockService {

    List<StockResponse> finAll();

    StockResponse findById(String id);
    StockResponse create(StockRequest stockRequest);

    ResponseEntity<?> update(StockRequest stockRequest);

    ResponseEntity<?> delete(String id);


}
