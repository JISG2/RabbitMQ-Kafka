package com.jisg.rabbitmq;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.Filter;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.MessagingException;

@SpringBootApplication
public class IntegrationRabbitApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(IntegrationRabbitApplication.class).web(WebApplicationType.NONE).run(args);
	}

	@Filter(inputChannel = "orderChannel",outputChannel = "transformerChannel")
	public boolean filterKeyHeader(final Message<String> message) {
		System.out.println("Filtering message");
		final MessageHeaders headers = message.getHeaders();
		System.out.println("MessageHeaders: "+headers);
		final String headerValue = (String) headers.getOrDefault("KEY", "123");
		System.out.println("headerValue: "+headerValue);
		final boolean filterResult = headerValue.equals("1234");
		System.out.println("Filter result: "+filterResult);
		return filterResult;
	}

	@Transformer(inputChannel = "transformerChannel", outputChannel = "invoiceChannel")
	String transformerMessage(String message) {
		System.out.println("Received message transformer: "+ message);
		return message+" transformer";
	}

	@Bean
	@ServiceActivator(inputChannel = "invoiceChannel")
	public MessageHandler handler() {
		return new MessageHandler() {
			@Override
			public void handleMessage(Message<?> message) throws MessagingException {
				System.out.println("Message: "+message.getPayload());
			}
		};
	}

}
