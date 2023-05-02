package com.ms.vallegrande.order.infraestructure.repository;

import com.ms.vallegrande.order.domain.OrderRequest;
import com.ms.vallegrande.order.domain.OrderResponse;
import com.ms.vallegrande.order.application.repository.OrderRepository;
import com.ms.vallegrande.order.domain.Product;
import com.ms.vallegrande.order.domain.Stock;
import com.ms.vallegrande.order.infraestructure.feign.ProductClientFeign;
import com.ms.vallegrande.order.infraestructure.feign.StockClientFeign;
import com.ms.vallegrande.order.infraestructure.repository.dto.Order;
import com.ms.vallegrande.order.infraestructure.repository.dto.Stockdto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public class OrderImpl implements OrderRepository {

    @Autowired
    MongoOperations mongoOperations;

    @Autowired
    ProductClientFeign productClientFeign;

    @Autowired
    StockClientFeign stockClientFeign;

    public OrderImpl(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    @Override
    public OrderResponse save(OrderRequest orderRequest) {
        Product product = productClientFeign.findItemById(orderRequest.getProduct().getId());
        Stock stock = this.mongoOperations.findOne(new Query(Criteria.where("idProduct").is(orderRequest.getProduct().getId())),Stock.class);

        System.out.println("producto  " + product);

        if(product != null){

            if(stock.getStock() >= orderRequest.getCantidad()){

                System.out.println("econtrado stock de producto   " + stock);

                int newCant = stock.getStock() - orderRequest.getCantidad();
                Stockdto stockdto= new Stockdto();
                stockdto.setStock(newCant);
                stockdto.setIdProduct(orderRequest.getProduct().getId());

                System.out.println("valores  " + stockdto);

                stockClientFeign.update(stockdto);

                Order order = new Order();
                order.setId(UUID.randomUUID().toString());
                order.setUserId(orderRequest.getUserId());
                order.setProduct(product);
                order.setCantidad(orderRequest.getCantidad());
                order.setStatus(orderRequest.getStatus());

                this.mongoOperations.save(order);

                OrderResponse response = new OrderResponse();
                response.setId(order.getId());
                response.setStatus("ORDEN EN PROCESO");
                return response;
            }else {
                OrderResponse response = new OrderResponse();
                response.setStatus("STOCK NO DISPONIBLE");
                return response;
            }

        }
        return null;
    }

    @Override
    public void update(OrderRequest orderRequest) {
        this.mongoOperations.save(orderRequest);
    }

}
