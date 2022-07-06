package br.edu.ifpb.pweb2.caramelodasorte.service;

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
        emailSenderService.sendEmail(email);
    }
}
