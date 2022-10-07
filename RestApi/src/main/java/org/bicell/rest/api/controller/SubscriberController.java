package org.bicell.rest.api.controller;

import org.bicell.rest.api.entity.Subscriber;
import org.bicell.rest.api.repository.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin //for CORS
@RequestMapping("/subscribe")
public class SubscriberController {

    @Autowired
    SubscriberRepository subscriberRepository=new SubscriberRepository();

    @GetMapping("/get-sub-by-msisdn")
    public Subscriber getSubscriberByMSISDN(@RequestBody Subscriber subscriber) throws Exception{
        return subscriberRepository.getSubscriberByMSISDN(subscriber.getMsisdn());
    }

    @GetMapping("/get-all-subs")
    public List<Subscriber> getAllSubscribers() throws Exception{
        return subscriberRepository.getAllSubscribers();
    }

    //TODO return type check
    @PostMapping("/add-sub")
    public void addNewSubscriber(@RequestBody Subscriber subscriber) throws Exception{
        subscriberRepository.addNewSubscriberToOracle(subscriber);
        //subscriberRepository.addNewSubscriberToVoltDb(subscriber);
    }



}
