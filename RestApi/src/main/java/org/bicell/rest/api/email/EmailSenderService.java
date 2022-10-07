package org.bicell.rest.api.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    public EmailSenderService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    private JavaMailSender mailSender;

    public void sendEmail(String toEmail,
                          String subject,
                          String body){

        SimpleMailMessage mailMessage=new SimpleMailMessage();

        mailMessage.setFrom("i2i.ocs.project@gmail.com");
        mailMessage.setTo(toEmail);
        mailMessage.setText(body);
        mailMessage.setSubject(subject);

        mailSender.send(mailMessage);

        System.out.println("Mail sent successfully.");
    }

}
