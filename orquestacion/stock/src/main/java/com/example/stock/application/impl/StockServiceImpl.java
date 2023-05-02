package com.example.stock.application.impl;

import com.example.stock.application.StockService;
import com.example.stock.application.repository.StockRepository;
import com.example.stock.domain.StockRequest;
import com.example.stock.domain.StockResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    private StockRepository stockRepository;

    public StockServiceImpl(StockRepository stockRepository){
        this.stockRepository = stockRepository;
    }
    @Override
    public List<StockResponse> finAll() {
        return stockRepository.finAll();
    }

    @Override
    public StockResponse findById(String id) {
        return stockRepository.findById(id);
    }

    @Override
    public StockResponse create(StockRequest stockRequest) {
        return stockRepository.create(stockRequest);
    }

    @Override
    public ResponseEntity<?> update(StockRequest stockRequest) {
        stockRepository.update(stockRequest);

        return new ResponseEntity<>("Stock Actualizado", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> delete(String id) {

        stockRepository.delete(id);
        return new ResponseEntity<>("Stock Eliminado", HttpStatus.CREATED);
    }
}
