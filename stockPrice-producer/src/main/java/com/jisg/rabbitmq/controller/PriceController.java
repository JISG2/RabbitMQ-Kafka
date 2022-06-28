package com.jisg.rabbitmq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jisg.rabbitmq.constants.RabbitMQConstants;
import com.jisg.rabbitmq.dto.PriceDto;
import com.jisg.rabbitmq.service.RabbitMQService;

@RestController
@RequestMapping(value = "/price")
public class PriceController {
	
	@Autowired
	private RabbitMQService rabbitmqService;
	
	@PutMapping
	private ResponseEntity alterPrice(@RequestBody PriceDto priceDto) {
		System.out.println(priceDto.codeProduct);
		this.rabbitmqService.sendMessage(RabbitMQConstants.QUEUE_PRICE, priceDto);
		return new ResponseEntity(HttpStatus.OK);
	}

}
