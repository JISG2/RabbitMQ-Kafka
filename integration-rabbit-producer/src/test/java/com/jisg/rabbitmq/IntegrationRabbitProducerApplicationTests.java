package com.jisg.rabbitmq;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;


import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
class IntegrationRabbitProducerApplicationTests {

	@InjectMocks
	static RabbitTemplate rabbitTemplateMock;
	@Before
	  public void setUp() {
	    rabbitTemplateMock = Mockito.mock(RabbitTemplate.class);
	  }
	@Test
	public void testBroadcast() {
		Mockito.verify(rabbitTemplateMock).convertAndSend(eq("orderChannel"), eq("orders.2"), eq("Test"));
	}

}
