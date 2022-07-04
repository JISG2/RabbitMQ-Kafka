package com.jisg.rabbitmq;

import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

	private final RabbitTemplate rabbitTemplate;

	public Runner(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Sending first message");

		rabbitTemplate.convertAndSend(ConfigProducerRabbitMQ.topicExchangeName,"orders.1", "Hello world JISG", m -> {
			m.getMessageProperties().setHeader("KEY", "1234"); 
			return m; 
		});

		//TimeUnit.SECONDS.sleep(5); 
		//System.out.println("Sending second message");
		//rabbitTemplate.convertAndSend(ConfigProducerRabbitMQ.topicExchangeName,"orders.1", "Hello world JISG");

	}
}
