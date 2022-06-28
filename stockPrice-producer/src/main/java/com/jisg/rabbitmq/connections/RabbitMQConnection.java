package com.jisg.rabbitmq.connections;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import com.jisg.rabbitmq.constants.RabbitMQConstants;

@Component
public class RabbitMQConnection {
	
	private static final String NAME_EXCHANGE = "amq.direct";
	private AmqpAdmin amqpAdmin;
	
	public RabbitMQConnection(AmqpAdmin amqpAdmin) {
		this.amqpAdmin = amqpAdmin;	
	}
	
	private Queue queue(String nameQueue) {
		return new Queue(nameQueue,true,false,false);
	}
	
	private DirectExchange directExchange() {
		return new DirectExchange(NAME_EXCHANGE);
	}
	
	private Binding bindging(Queue queue, DirectExchange direct) {
		return new Binding(queue.getName(), Binding.DestinationType.QUEUE, direct.getName(), queue.getName(),null);
	}
	
	@PostConstruct
	private void add() {
		Queue queueStock = this.queue(RabbitMQConstants.QUEUE_STOCK);
		Queue queuePrice = this.queue(RabbitMQConstants.QUEUE_PRICE);
		
		DirectExchange direct = this.directExchange();
		
		Binding bindingStock = this.bindging(queueStock, direct);
		Binding bindingPrice = this.bindging(queuePrice, direct);
		
		//Declarando filas en RabbitMQ
		this.amqpAdmin.declareQueue(queueStock);
		this.amqpAdmin.declareQueue(queuePrice);
		
		this.amqpAdmin.declareExchange(direct);
		
		this.amqpAdmin.declareBinding(bindingStock);
		this.amqpAdmin.declareBinding(bindingPrice);
	}

}
