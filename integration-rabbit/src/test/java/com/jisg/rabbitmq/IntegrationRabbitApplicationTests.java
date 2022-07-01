package com.jisg.rabbitmq;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IntegrationRabbitApplicationTests {

	@BeforeEach
	public void setup(){
		System.out.println("Inicio de la prueba");
	}
	@AfterEach
	public void destroy(){
		System.out.println("Fin de la prueba");
	}
	@Test
	public void pruebaGetRolAmazonCloud(){   
		System.out.println("Primera prueba..");  	
	}	

}
