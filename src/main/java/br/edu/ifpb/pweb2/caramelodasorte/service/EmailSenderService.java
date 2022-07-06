package br.edu.ifpb.pweb2.caramelodasorte.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

    @Autowired
    JavaMailSender mailSender;

    public void sendEmail(String toEmail){

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("caramelodasorte@gmail.com");
        message.setTo(toEmail);
        message.setText("Fala, pipoca! pitbull tá na pista! Au! Au! Um sorteio foi realizado! Corra para ver o resultado! ruf! ruf!");
        message.setSubject("Um Sorteio foi realizado!!");
        mailSender.send(message);

    }
}
