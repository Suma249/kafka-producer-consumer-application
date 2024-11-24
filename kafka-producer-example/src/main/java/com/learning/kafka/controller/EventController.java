package com.learning.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.kafka.dto.CustomerDto;
import com.learning.kafka.service.KafkaMessagePublisher;

@RestController
@RequestMapping("/producer-app")
public class EventController {

	@Autowired
	private KafkaMessagePublisher publisher;
	
	@GetMapping("/publish/{message}")
	public ResponseEntity<?> publishMessage(@PathVariable String message){
		try {
			for(int i=0;i<=50;i++)
			publisher.sendMessageToTopic(message+i);
			return ResponseEntity.ok("message published successfully");
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PostMapping("/publish")
	public void sendEvents(@RequestBody CustomerDto customer) {
		publisher.sendEventsToTopic(customer);
	}
}
