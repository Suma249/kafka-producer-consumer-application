package com.learning.kafka.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.learning.kafka.dto.CustomerDto;

@Service
public class KafkaMessagePublisher {
	
	@Autowired
	private KafkaTemplate<String, Object> template;
	
	public void sendMessageToTopic(String message) {
		/*CompletableFuture<SendResult<String, Object>> future=template.send("string-data-topic",2,null,message);
		//to block sending thread and get he resukt about the send messages, we can do futre.get than it will wait for the reskts
		future.whenComplete((result,ex)->{
			if(ex==null)
				System.out.println("sent message=["+message+"] with offset=["+result.getRecordMetadata().offset()+"]");
			else
				System.out.println("unable to send message["+message+"] dure to"+ex.getMessage());
		});*/
		
		template.send("string-data-topic",2,null,"I am partition 2");
		template.send("string-data-topic",0,null,"I am partition 0");
		template.send("string-data-topic",0,null,"I am partition 0");
		template.send("string-data-topic",1,null,"I am partition 1");
		template.send("string-data-topic",2,null,"I am partition 2");
		
	}
	
	public void sendEventsToTopic(CustomerDto customer) {
		try {
			customer.toString();
		CompletableFuture<SendResult<String, Object>> future=template.send("object-data-topic",customer);
		//to block sending thread and get he resukt about the send messages, we can do futre.get than it will wait for the reskts
		future.whenComplete((result,ex)->{
			if(ex==null)
				System.out.println("sent message=["+customer.toString()+"] with offset=["+result.getRecordMetadata().offset()+"]");
			else {
				System.out.println("unable to send message["+customer.toString()+"] dure to"+ex.getMessage());
				ex.printStackTrace();
			}
		});
		}
		catch(Exception e) {
			System.out.println("exception occurred: "+e.getMessage());
		}
	}
}
