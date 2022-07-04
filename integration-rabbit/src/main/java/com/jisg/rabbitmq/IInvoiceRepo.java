package com.jisg.rabbitmq;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInvoiceRepo extends MongoRepository<Invoice, String>{

}
