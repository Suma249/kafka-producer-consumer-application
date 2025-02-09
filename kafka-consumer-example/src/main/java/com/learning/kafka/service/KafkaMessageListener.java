package com.learning.kafka.service;

import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.learning.kafka.dto.CustomerDto;

import ch.qos.logback.classic.Logger;

@Service
public class KafkaMessageListener {
	Logger logger=(Logger) LoggerFactory.getLogger(KafkaMessageListener.class);
	
    @KafkaListener(topics="object-data-topic", groupId="consumer-group2") //for object data we need to serialize anddeseraliza data
	public void Consume1(CustomerDto customer) {
		logger.info("consumer1 consumed the message: "+customer.toString());
	}
	
   /* @KafkaListener(topics="string-data-topic", groupId="consumer-group1")
	public void Consume2(String message) {
		logger.info("consumer2 consumed the message: "+message);
	}*/
	
/*	@KafkaListener(topics="string-data-topic", groupId="consumer-group1")
	public void Consume3(String message) {
		logger.info("consumer3 consumed the message: "+message);
	}
	
	@KafkaListener(topics="string-data-topic", groupId="consumer-group1")
	public void Consume4(String message) {
		logger.info("consumer4 consumed the message: "+message);
	} */
}
