package org.bicell.rest.api.controller;

import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.ValidPort;
import org.bicell.rest.api.email.EmailSenderService;
import org.bicell.rest.api.encryption.Encryption;
import org.bicell.rest.api.entity.Subscriber;
import org.bicell.rest.api.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin //for CORS
@RequestMapping("/login")
public class LoginController {
    LoginRepository loginRepository;
    public LoginController(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }


    @ApiOperation("Subscriber login")
    @PostMapping("/")
    public ResponseEntity login(@RequestBody Subscriber subscriber) throws Exception{
        return loginRepository.loginCheck(subscriber.getMsisdn(), subscriber.getPassword());

    }

    @ApiOperation("Subscriber forgot password")
    @PostMapping("/forgot-password")
    public ResponseEntity forgotPassword(@RequestBody Subscriber subscriber) throws Exception{
        return loginRepository.forgotPassword(subscriber.getEmail());
    }


}
