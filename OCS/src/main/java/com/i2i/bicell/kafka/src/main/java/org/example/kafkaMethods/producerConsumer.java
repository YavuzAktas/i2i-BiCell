package com.i2i.bicell.kafka.src.main.java.org.example.kafkaMethods;

import com.i2i.bicell.kafka.src.main.java.org.example.modul.*;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.*;
import com.i2i.bicell.kafka.src.main.java.org.example.configuration.IKafkaConfiguration;

import java.io.Serializable;
import java.time.Duration;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;
import java.util.logging.*;


public class producerConsumer {
    private static Logger logger = LogManager.getLogManager().getLogger(String.valueOf(producerConsumer.class).toString());

    public static void runConsumer() {

        Consumer<Long, UsageMessage> consumer = UsageMessageConsumer.createConsumer();

        while (true) {
            ConsumerRecords<Long, UsageMessage> consumerRecords = consumer.poll(Duration.ofSeconds(1));
            if (consumerRecords.count() == 0) {
                logger.info("No message to read");
                continue;
            }
            for (ConsumerRecord<Long, UsageMessage> record : consumerRecords) {
                logger.info("Message received");
                logger.info(
                        record.toString()
                );
            }
            consumer.commitAsync();
        }
    }

    private static Serializable SerializeUsage(UsageMessage msg){
        return msg;
    }

    public static void runProducer(UsageMessage msg) {
        Producer<Long, Serializable> producer = UsageMessageProducer.createProducer();
        logger.info("Producer created.");
        ProducerRecord<Long,Serializable> record = new ProducerRecord<>(IKafkaConfiguration.topicName,msg);
        try{
            producer.send(record).get();
            logger.info("Message Send");
        }
        catch (ExecutionException e){
            logger.info("Error during producer send : ");
            logger.info((Supplier<String>) e);
        }
        catch (InterruptedException e){
            logger.info("Error during producer send : ");
            logger.info((Supplier<String>) e);
        }
    }
}
