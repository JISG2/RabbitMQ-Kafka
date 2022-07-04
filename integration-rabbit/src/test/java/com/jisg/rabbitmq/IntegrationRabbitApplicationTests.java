package com.jisg.rabbitmq;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class IntegrationRabbitApplicationTests {

	@Test
	public void testReceiveMessage(){   
		System.out.println("Primera prueba..");  	
	}	

}
