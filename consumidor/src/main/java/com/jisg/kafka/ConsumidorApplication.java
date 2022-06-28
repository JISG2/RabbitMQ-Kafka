package com.jisg.kafka;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;


@SpringBootApplication
public class ConsumidorApplication {
	
	private static final Logger log = LoggerFactory.getLogger(ConsumidorApplication.class);
	
	@KafkaListener(topics = "jisg-topic",containerFactory = "listenerContainerFactory",groupId = "jisg-group",properties ={"max.poll.interval.ms:4000","max.poll.records:10"})
	public void listen(List<String> messages) {
		
		log.info("Start reading messages");
		for(String message: messages) {
			log.info("Message received =  {}", message);
		}
		log.info("Batch complete");
		
	}

	public static void main(String[] args) {
		SpringApplication.run(ConsumidorApplication.class, args);
		
	}
	

}
