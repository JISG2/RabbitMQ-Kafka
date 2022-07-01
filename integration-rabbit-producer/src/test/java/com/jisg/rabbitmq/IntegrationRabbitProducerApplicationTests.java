package com.jisg.rabbitmq;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.test.TestRabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class IntegrationRabbitProducerApplicationTests {
	
	@InjectMocks
    private TestRabbitTemplate template;

    @Test
    public void testSendAndReceive() {
    	System.out.println("Inicio de la prueba");
		when(template.convertSendAndReceive("orderChannel", "orders.1", "Hello world JISG")).thenReturn("baz:hello");
        assertEquals(this.template.convertSendAndReceive("orderChannel", "orders.1", "Hello world JISG"), "baz:hello");
    }
}
