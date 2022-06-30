package com.jisg.rabbitmq;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
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
        rabbitTemplate.convertAndSend(ConfigProducerRabbitMQ.topicExchangeName, "orders.1", "Hello world JISG", new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                //
                message.getMessageProperties().setHeader("KEY", "123456");
                return message;
            }
        });
		TimeUnit.SECONDS.sleep(5);
		System.out.println("Sending second message");
		rabbitTemplate.convertAndSend(ConfigProducerRabbitMQ.topicExchangeName, "orders.2", "Hello world JISG 2", new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                //
                message.getMessageProperties().setHeader("KEY", "1234");
                return message;
            }
        });
	}
}
