package com.jisg.rabbitmq.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
@Service
public class RabbitMQService {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	  private ObjectMapper objectMapper;

	public void sendMessage(String nameQueue, Object message) {
		try {
		      String mensagemJson = this.objectMapper.writeValueAsString(message);
		      this.rabbitTemplate.convertAndSend(nameQueue, mensagemJson);
	    } catch (Exception e){
	      e.printStackTrace();
	    }
	}
}
