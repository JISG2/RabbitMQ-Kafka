package com.jisg.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.jisg.rabbitmq.constants.RabbitMQConstants;
import com.jisg.rabbitmq.dto.StockDto;

@Component
public class StockConsumer {
	
	@RabbitListener(queues = RabbitMQConstants.QUEUE_STOCK)
	private void consumer(String mensagem) throws JsonProcessingException, InterruptedException  {
		StockDto stockDto = new ObjectMapper().readValue(mensagem, StockDto.class);
		System.out.println("Message received");
		System.out.println(stockDto.codeProduct);
		System.out.println(stockDto.quantity);
		System.out.println("----------------------");
	}

}
