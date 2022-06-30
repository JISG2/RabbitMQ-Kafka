package com.jisg.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IntegrationRabbitProducerApplication{
	
	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(IntegrationRabbitProducerApplication.class, args).close();
	}


}
