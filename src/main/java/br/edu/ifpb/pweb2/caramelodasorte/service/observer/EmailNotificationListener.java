package br.edu.ifpb.pweb2.caramelodasorte.service.observer;

import br.edu.ifpb.pweb2.caramelodasorte.service.EmailSenderService;
import br.edu.ifpb.pweb2.caramelodasorte.service.observer.EventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationListener implements EventListener {

    @Autowired
    EmailSenderService emailSenderService;

    @Override
    public void update(String email) {
        String body = "Fala, pipoca! pitbull tá na pista! Au! Au! Um sorteio foi realizado! Corra para ver o resultado! ruf! ruf!";
        String subject = "Um Sorteio foi realizado!!";
        emailSenderService.sendEmail(email, body, subject );
    }
}
