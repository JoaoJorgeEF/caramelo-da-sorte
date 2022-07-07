package br.edu.ifpb.pweb2.caramelodasorte.service.chainOfResponsability;

import br.edu.ifpb.pweb2.caramelodasorte.model.Apostador;
import br.edu.ifpb.pweb2.caramelodasorte.model.Sorteio;
import br.edu.ifpb.pweb2.caramelodasorte.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationMiddleware extends Middleware {

    @Autowired
    private EmailSenderService emailSenderService;

    @Override
    public Boolean check(Sorteio sorteio, Apostador apostador) {
        String body = "Fala, pipoca! pitbull tá na pista! Au! Au! Um sorteio foi realizado e adivinha?! Você é o ganhador!!! ruf ruf !! Seu prêmio é de: R$ " + sorteio.getValor() + " Vá curtir sua cachorrada, ruf!! ruf !!";
        String subject = "Parabéns!! Você ganhou um sorteio!!";
        emailSenderService.sendEmail(apostador.getEmail(), body, subject);

        return checkNext(sorteio, apostador);
    }
}
