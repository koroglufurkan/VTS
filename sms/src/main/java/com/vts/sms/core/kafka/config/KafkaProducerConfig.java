package com.vts.sms.core.kafka.config;

import com.vts.sms.data.Ship;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;
//Main Purpose is to initialize a KafkaTemplate
@Configuration
public class KafkaProducerConfig {
    //Direct Initialization
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
    //Method To Get Configuration
    public Map<String, Object> producerConfig(){
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
        return props;
    }
    //Default factory is created
    @Bean
    public ProducerFactory<Integer, Ship> producerFactory(){
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }
    //kafkaTemplate (controls kafka topics) is created by default factory
    @Bean
    public KafkaTemplate<Integer, Ship> kafkaTemplate(ProducerFactory<Integer, Ship> producerFactory){
        return new KafkaTemplate<>(producerFactory);
    }
}


