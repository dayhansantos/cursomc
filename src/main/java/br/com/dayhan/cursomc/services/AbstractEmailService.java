package br.com.dayhan.cursomc.services;

import br.com.dayhan.cursomc.domain.Pedido;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

public abstract class AbstractEmailService implements  EmailService{

    @Value("${default.sender}")
    private String sender;

    @Override
    public void sendOrderConfirmationEmail(Pedido pedido) {
        final var msg = prepareSimpleMailMessageFromPedido(pedido);
        sendEmail(msg);
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido pedido) {
        final var sm = new SimpleMailMessage();
        sm.setTo(pedido.getCliente().getEmail());
        sm.setFrom(this.sender);
        sm.setSubject("Pedido confirmado! Código: " + pedido.getId());
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(pedido.toString());
        return sm;
    }
}
