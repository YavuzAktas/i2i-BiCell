package com.i2i.bicell.kafka.src.main.java.org.example.modul;


import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.LongDeserializer;
import com.i2i.bicell.kafka.src.main.java.org.example.configuration.IKafkaConfiguration;

import java.util.Collections;
import java.util.Properties;

public class UsageMessageConsumer {
    public static Consumer<Long, UsageMessage> createConsumer(){
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, IKafkaConfiguration.bootstrapServers);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.example.modul.DeseriliazeUsageMessage");

        Consumer<Long, UsageMessage> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singletonList(IKafkaConfiguration.topicName));
        return consumer;
    }
}
