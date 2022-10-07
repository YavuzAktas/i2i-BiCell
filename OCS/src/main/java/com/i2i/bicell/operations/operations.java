package com.i2i.bicell.operations;

import java.util.Date;
import com.i2i.bicell.VoltDb.VoltDb;
import org.apache.logging.log4j.*;
import com.i2i.bicell.Message.Message;
import com.i2i.bicell.kafka.src.main.java.org.example.modul.UsageMessage;

public class operations{
    private Logger log = LogManager.getLogger(operations.class);
    private VoltDb Voltdb;
    private UsageMessage kafkaUsageMessage;

    public operations(VoltDb voltdb){
        log.info("Operation Constructor");
        this.Voltdb = voltdb;
    }
    public void start(Object obj){
        log.info("Start operations constructor");

        int subsc_id = ((Message.usage)obj).getSubsc_id();
        int package_id = ((Message.usage)obj).getPackage_id();
        Date sdate = ((Message.usage)obj).getSdate();
        Date edate = ((Message.usage)obj).getEdate();
        int blc_voice = ((Message.usage)obj).getBlc_voice();
        int blc_sms = ((Message.usage)obj).getBlc_sms();
        int blc_data = ((Message.usage)obj).getBlc_data();

    }
    public void sender_voltdb(String MSISDN, String service, int amount, int price){
        log.info("Sender VoltDb started");
        if(service.toUpperCase().equals("SMS")){
            log.info("SMS data send voltdb from Operations Class");
            Voltdb.send_SmsAmount(MSISDN,amount,price);
        }
        if(service.toUpperCase().equals("VOICE")){
            log.info("VOICE data send voltdb from Operations Class");
            Voltdb.send_VoiceAmount(MSISDN,(int)(amount/60),price);
        }
        if(service.toUpperCase().equals("DATA")){
            log.info("DATA data send voltdb from Operations Class");
            Voltdb.send_GbAmount(MSISDN,amount,price);
        }
    }
    public UsageMessage sender_kafka(String MSISDN , int Sbsc_id, int package_id, java.sql.Date sdate, java.sql.Date edate, int blc_voice, int blc_sms, int blc_data, int price){
        log.info("Sender Kafka started");
        //ProducerMethod.runProducer(kafkaUsageMessage);
        kafkaUsageMessage = new UsageMessage();
        int sub_id = (int) Voltdb.get_UidByMSISDN(MSISDN);

        kafkaUsageMessage.setSubsc_id(Sbsc_id);
        kafkaUsageMessage.setPackage_id(package_id);
        kafkaUsageMessage.setSdate(sdate);
        kafkaUsageMessage.setEdate(edate);
        kafkaUsageMessage.setBlc_voice(blc_voice);
        kafkaUsageMessage.setBlc_sms(blc_sms);
        kafkaUsageMessage.setBlc_data(blc_data);
        kafkaUsageMessage.setPrice(price);

        return kafkaUsageMessage;
    }
}