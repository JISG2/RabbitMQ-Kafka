package com.jisg.rabbitmq;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class IntegrationRabbitProducerApplicationTests {
	
	@InjectMocks
    private Runner runner;
	@Mock
	static RabbitTemplate rabbitTemplate;

    @Test
    public void testSendAndReceive() {
    	try {
			runner.run("");
		} catch (Exception e) {
			e.printStackTrace();
		}
    	verify(rabbitTemplate,times(1)).convertAndSend("orderChannel","orders.1","Hello world JISG");
    }
}
