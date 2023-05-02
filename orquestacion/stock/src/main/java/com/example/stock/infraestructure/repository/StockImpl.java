package com.example.stock.infraestructure.repository;

import com.example.stock.application.repository.StockRepository;
import com.example.stock.domain.Product;
import com.example.stock.domain.StockRequest;
import com.example.stock.domain.StockResponse;
import com.example.stock.infraestructure.feign.ProductClientFeign;
import com.example.stock.infraestructure.repository.dto.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class StockImpl implements StockRepository {

    @Autowired
    private final MongoOperations mongoOperations;

    @Autowired
    ProductClientFeign productClientFeign;

    protected StockImpl(MongoOperations mongoOperations){
        this.mongoOperations = mongoOperations;
    }

    @Override
    public List<StockResponse> finAll() {

        List<Stock> stocks = this.mongoOperations.findAll(Stock.class);
        List<StockResponse> stockResponses = new ArrayList<>();

        stocks.forEach(s -> {
            StockResponse response = getStockResponse(s);
            stockResponses.add(response);
        });

        return stockResponses;
    }

    @Override
    public StockResponse findById(String id) {

        Stock stock = this.mongoOperations.findOne(new Query(Criteria.where("id").is(id)),Stock.class);

        StockResponse response = getStockResponse(stock);
        return response;
    }

    @Override
    public StockResponse create(StockRequest stockRequest) {

        Product product = productClientFeign.findById(stockRequest.getIdProduct());

        if(product != null){
            Stock stock = new Stock();
            stock.setId(UUID.randomUUID().toString());
            stock.setIdProduct(stockRequest.getIdProduct());
            stock.setStock(stockRequest.getStock());

            this.mongoOperations.save(stock);

            StockResponse response = new StockResponse();
            response.setId(stock.getId());
            response.setIdProduct(stock.getIdProduct());
            response.setStock(stock.getStock());
            return response;

        }

        return null;
    }

    @Override
    public void update(StockRequest stockRequest) {
        Stock stock = this.mongoOperations.findOne(new Query(Criteria.where("idProduct").is(stockRequest.getIdProduct())),Stock.class);
        stock.setIdProduct(stockRequest.getIdProduct());
        stock.setStock(stockRequest.getStock());

        this.mongoOperations.save(stock);
    }

    @Override
    public void delete(String id) {
        this.mongoOperations.findAndRemove(new Query(Criteria.where("id").is(id)),Stock.class);

    }


    private StockResponse getStockResponse(Stock s){
        StockResponse response = new StockResponse();
        response.setId(s.getId());
        response.setIdProduct(s.getIdProduct());
        response.setStock(s.getStock());
        return response;
    }
}
