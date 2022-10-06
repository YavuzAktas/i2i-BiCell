package com.i2i.bicell.price;

import com.i2i.bicell.VoltDb.VoltDb;
import org.apache.logging.log4j.*;

import java.io.IOException;
import java.util.*;
import java.util.regex.*;

import com.i2i.bicell.parsexml.parseXml;
import com.i2i.bicell.Message.Message.service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

public class price{
    private Logger log = LogManager.getLogger(price.class);
    private service sms_service=null;
    private service voice_service=null;
    private service net_service=null;
    private int price;

    /*private boolean control(String opnumber, String regex){
        // this control for regex
        log.info("Regex Control");
        Pattern p = Pattern.compile(regex);
        // opnumber for msısdn
        Matcher m = p.matcher(opnumber);

        return m.find();
    }*/
    public price() throws ParserConfigurationException, IOException, SAXException {
        log.info("Price Constructor");
        parseXml parse = new parseXml();
        List<service> list_of_service = parse.get_Service_Info();
        // 0-> voice, 1-> sms, 2-> gb
        voice_service = list_of_service.get(0);
        sms_service = list_of_service.get(1);
        net_service = list_of_service.get(2);
    }
    public int get_used(String opnumber, int amount, String service){
        log.info("Price calculation started");
        price =0;
        if(service.toUpperCase().equals("VOICE") /*&& control(opnumber, sms_service.get_regex())*/){
            price = (int) (amount * voice_service.get_price());
            log.info("Voice price :"+price);
        }
        else if(service.toUpperCase().equals("SMS") /*&& control(opnumber, sms_service.get_regex())*/){
            price = (int) (amount * sms_service.get_price());
            log.info("SMS price :"+price);
        }
        else if(service.toUpperCase().equals("DATA") /*&& control(opnumber, sms_service.get_regex())*/){
            price = (int) (amount / net_service.get_price());
            log.info("Voice price :"+price);
        }
        log.info("Total Price: "+price);
        return price;
    }
}