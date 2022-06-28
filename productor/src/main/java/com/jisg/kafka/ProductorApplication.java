package com.jisg.kafka;


import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;

@SpringBootApplication
public class ProductorApplication{
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired
	private CountDownLatchHandler countDownLatchHandler;

	public static void main(String[] args) {
		//SpringApplication.run(ProductorApplication.class, args);
		ConfigurableApplicationContext context = new SpringApplicationBuilder(ProductorApplication.class).web(WebApplicationType.NONE).run(args);
		context.getBean(ProductorApplication.class).run(context);
		context.close();
	}
	

	private void run(ConfigurableApplicationContext context){

		System.out.println("Inside ProducerApplication run method...");

		MessageChannel producerChannel = context.getBean("producerChannel", MessageChannel.class);

		for (int i = 0; i <10; i++) {
			Map<String, Object> headers = Collections.singletonMap(KafkaHeaders.TOPIC, "jisg-topic");
			producerChannel.send(new GenericMessage<>(String.format("Sample message %d", i), headers));
		}
		
		for (int i = 0; i <10; i++) {
			kafkaTemplate.send("jisg-topic",String.format("Sample message %d", i));
		}
		System.out.println("Finished ProducerApplication run method...");
		
		try {
			countDownLatchHandler.getLatch().await(10000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 System.out.println("count: "+countDownLatchHandler.getLatch().getCount());
	}

}
