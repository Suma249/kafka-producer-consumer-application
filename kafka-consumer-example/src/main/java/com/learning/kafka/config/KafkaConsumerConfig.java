package com.learning.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.learning.kafka.dto.CustomerDto;

import org.apache.kafka.clients.consumer.ConsumerConfig;


@Configuration
public class KafkaConsumerConfig {
	
	@Bean
	public Map<String,Object> consumerConfig(){
		Map<String, Object> props=new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "consumer-group2");
		//props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, CustomerDto.class);
		return props;
	}
	
	@Bean
 	public ConsumerFactory<String, Object> consumerFactory(){
		return  new DefaultKafkaConsumerFactory<String, Object>(consumerConfig());
	}
	
	@Bean
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String,Object>> kafkaListenerContainerFactory(){
		ConcurrentKafkaListenerContainerFactory<String,Object> factory=new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory((ConsumerFactory<? super String, ? super Object>) consumerFactory());
		return factory;
	}
	
	
}
