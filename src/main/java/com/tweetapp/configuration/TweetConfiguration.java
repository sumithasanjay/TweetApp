//package com.tweetapp.configuration;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.core.ProducerFactory;
//
//import com.fasterxml.jackson.databind.JsonSerializer;
//import com.tweetapp.app.model.Tweet;
//
//@Configuration
//public class TweetConfiguration {
//	
//	@Bean
//	public ProducerFactory<String, Tweet> producerFactory(){
//		Map<String,Object> config=new HashMap<>();
//		
//		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
//		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, com.fasterxml.jackson.databind.ser.std.StringSerializer.class);
//		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//
//		return new DefaultKafkaProducerFactory<>(config);
//		
//	}
//	@Bean
//	public KafkaTemplate<String, Tweet> kafkaTemplate(){
//		return new KafkaTemplate<>(producerFactory());
//	}
//	
//
//}
