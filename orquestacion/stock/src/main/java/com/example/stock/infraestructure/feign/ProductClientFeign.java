package com.example.stock.infraestructure.feign;
import com.example.stock.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name ="product-service")
public interface ProductClientFeign {

    @GetMapping(value = "/product/{id}")
    Product findById(@PathVariable String id);
}
