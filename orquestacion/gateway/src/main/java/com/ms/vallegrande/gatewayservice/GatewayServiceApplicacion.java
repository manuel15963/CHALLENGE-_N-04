package com.ms.vallegrande.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GatewayServiceApplicacion {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplicacion.class, args);
	}

}
