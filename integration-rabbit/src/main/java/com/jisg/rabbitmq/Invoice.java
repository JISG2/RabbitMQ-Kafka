package com.jisg.rabbitmq;

import org.springframework.data.annotation.Id;

public class Invoice {
	
	@Id
	public String id;
	public String message;
	
	
	public Invoice( String message) {
		this.message = message;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Invoice [id=" + id + ", message=" + message + "]";
	}
	
	
	

}
