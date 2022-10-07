package org.bicell.rest.api.controller;

import org.bicell.rest.api.entity.Balance;
import org.bicell.rest.api.entity.Subscriber;
import org.bicell.rest.api.repository.BalanceRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin //for CORS
@RequestMapping("/balance")
public class BalanceController {

    BalanceRepository balanceRepository;

    public BalanceController(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    @GetMapping("/get-dummy-balance")
    public List<Balance> deneme() throws Exception{//@RequestBody Subscriber subscriber
        return balanceRepository.deneme();//subscriber.getMsisdn()
    }

/*    @GetMapping("/get-balance-by-msisdn")
    public List<Balance> getBalanceByMSISDN(@RequestBody Subscriber subscriber){
        balanceRepository.deneme(subscriber.getMsisdn());
        return null;
    }*/
}
