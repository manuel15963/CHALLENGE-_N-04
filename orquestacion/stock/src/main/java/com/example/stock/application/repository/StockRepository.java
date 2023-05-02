package com.example.stock.application.repository;

import com.example.stock.domain.StockRequest;
import com.example.stock.domain.StockResponse;

import java.util.List;
import java.util.Optional;

public interface StockRepository {

    List<StockResponse> finAll();

    StockResponse findById(String id);
    StockResponse create(StockRequest stockRequest);

    void update(StockRequest stockRequest);

    void delete(String id);

}
