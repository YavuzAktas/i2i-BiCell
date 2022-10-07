package com.i2i.bicell.kafka.src.main.java.org.example.modul;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;

import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;
import java.util.function.Supplier;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class DeseriliazeUsageMessage implements Deserializer<UsageMessage>
{
    private Logger logger=LogManager.getLogManager().getLogger(DeseriliazeUsageMessage.class.toString());
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public UsageMessage deserialize(String topic, byte[] data) {
        try {
            if (data == null){
                logger.info("Null received at deserializing");
                return null;
            }
            logger.info("Deserializing...");
            return objectMapper.readValue(new String(data, "UTF-8"), UsageMessage.class);
        } catch (Exception e) {
            logger.info((Supplier<String>) e);
            throw new SerializationException("Error when deserializing byte[] to MessageDto");
        }
    }

    @Override
    public void close() {
    }
}
