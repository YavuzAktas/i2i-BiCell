package com.i2i.bicell.kafka.src.main.java.org.example.modul;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;


import java.util.Map;
import java.util.function.Supplier;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class SeriliazeUsageMessage implements Serializer<UsageMessage> {
    private Logger logger = LogManager.getLogManager().getLogger(SeriliazeUsageMessage.class.toString());

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public void close() {
    }


    @Override
    public byte[] serialize(String topic, UsageMessage data) {
        try {
            if (data == null) {
                logger.info("Null received at serializing");
                return null;
            }
            logger.info("Serializing...");
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            logger.info((Supplier<String>) e);
            throw new SerializationException("Error when serializing MessageDto to byte[]");
        }
    }
}
