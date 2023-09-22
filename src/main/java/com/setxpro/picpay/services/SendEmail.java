package com.setxpro.picpay.services;

import java.util.Properties;
import javax.mail.internet.*;
import javax.mail.Session;
import javax.mail.Authenticator;
import javax.mail.*;

public class SendEmail {
    public static void ServiceEmail(String body, String to, String subject) {
        // Configurações do servidor SMTP e autenticação
        String host = "smtp.gmail.com"; // Substitua pelo seu servidor SMTP
        String username = "developerseven777@gmail.com"; // Substitua pelo seu email
        String password = "gucabsksfemgiowu"; // Substitua pela sua senha

        // Configurações de propriedades
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587"); // A porta pode variar

        // Cria uma sessão de email
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Cria uma mensagem de email
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            // Envia o email
            Transport.send(message);

            System.out.println("Email enviado com sucesso!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}
