package com.jisg.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jisg.rabbitmq.constants.RabbitMQConstants;
import com.jisg.rabbitmq.dto.PriceDto;

@Component
public class PriceConsumer {
	
	@RabbitListener(queues = RabbitMQConstants.QUEUE_PRICE)
	private void consumer(String mensagem) throws JsonProcessingException, InterruptedException  {
		PriceDto priceDto = new ObjectMapper().readValue(mensagem, PriceDto.class);
		System.out.println("Message received");
		System.out.println(priceDto.codeProduct);
		System.out.println(priceDto.price);
		System.out.println("----------------------");
	}

}
