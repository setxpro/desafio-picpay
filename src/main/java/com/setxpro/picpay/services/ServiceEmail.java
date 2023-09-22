package com.setxpro.picpay.services;

import java.math.BigDecimal;
import java.util.Properties;
import javax.mail.internet.*;
import javax.mail.Session;
import javax.mail.Authenticator;
import javax.mail.*;

public class ServiceEmail {
    public static void Sender(String body, String to, String subject, BigDecimal value, String name) {
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

            String htmlContent = "" +
                    "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "  <head>\n" +
                    "    <style>\n" +
                    "        * {\n" +
                    "            margin: 0;\n" +
                    "            padding: 0;\n" +
                    "            box-sizing: border-box;\n" +
                    "            font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;\n" +
                    "        }\n" +
                    "        body {\n" +
                    "            display: flex;\n" +
                    "            flex-direction: column;\n" +
                    "        }\n" +
                    "        .header {\n" +
                    "            background-color: #053;\n" +
                    "            height: 70px;\n" +
                    "        }\n" +
                    "        .title {\n" +
                    "            font-weight: 400;\n" +
                    "            font-size: 3em;\n" +
                    "            color: #DDD;\n" +
                    "            text-align: center;\n" +
                    "        }\n" +
                    "\n" +
                    "        .middle {\n" +
                    "            background-color: #DDD;\n" +
                    "            padding: 2rem;\n" +
                    "        }\n" +
                    "        .footer {\n" +
                    "            height: 150px;\n" +
                    "            background-color: #053;\n" +
                    "            padding: 2rem;\n" +
                    "        }\n" +
                    "\n" +
                    "        table {\n" +
                    "            background-color: #EEE;\n" +
                    "        }\n" +
                    "\n" +
                    "        th {\n" +
                    "            background-color: #053;\n" +
                    "            color: #DDD;\n" +
                    "            padding: 5px;\n" +
                    "        }\n" +
                    "\n" +
                    "        td {\n" +
                    "            text-align: center;\n" +
                    "            color: #333;\n" +
                    "        }\n" +
                    "\n" +
                    "        h1,h4 {\n" +
                    "            font-weight: 400;\n" +
                    "        }\n" +
                    "\n" +
                    "        .initial {\n" +
                    "            height: 80px;\n" +
                    "        }\n" +
                    "\n" +
                    "        .space {\n" +
                    "            height: 200px;\n" +
                    "            line-height: 200px;\n" +
                    "        }\n" +
                    "\n" +
                    "        .a {\n" +
                    "            color: #333;\n" +
                    "            font-weight: bold;\n" +
                    "            margin-top: 100px;\n" +
                    "        }\n" +
                    "\n" +
                    "        a,p {\n" +
                    "            color: #FFF;\n" +
                    "        }\n" +
                    "\n" +
                    "\n" +
                    "    </style>\n" +
                    "    <meta charset=\"UTF-8\" />\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                    "    <title>Document</title>\n" +
                    "  </head>\n" +
                    "  <body>\n" +
                    "    <div class=\"header\">\n" +
                    "        <h1 class=\"title\">BAGAGGIO</h1>\n" +
                    "    </div>\n" +
                    "    <div class=\"middle\">\n" +
                    "        <div class=\"initial\">\n" +
                    "            <h1>Olá, "+name+".</h1>\n" +
                    "        </div>\n" +
                    "       <table width=\"400px\">\n" +
                    "            <thead>\n" +
                    "                <tr>\n" +
                    "                    <th>STATUS</th>\n" +
                    "                    <th>VALOR</th>\n" +
                    "                </tr>\n" +
                    "            </thead>\n" +
                    "            <tbody>\n" +
                    "                <tr>\n" +
                    "                    <td><strong>Sucesso</strong></td>\n" +
                    "                    <td><strong>R$ "+value+"</strong></td>\n" +
                    "                </tr>\n" +
                    "            </tbody>\n" +
                    "       </table>\n" +
                    "       <div class=\"space\">\n" +
                    "        <a href=\"http://localhost:5173/#/login-candidate\" class=\"a\">Click aqui para acessar a área do candidato.</a>\n" +
                    "       </div>\n" +
                    "    </div>\n" +
                    "    <div class=\"footer\">\n" +
                    "        <p>Atenciosamente,</p>\n" +
                    "        <a href=\"https://www.bagaggio.com.br\">BAGAGGIO</a>\n" +
                    "    </div>\n" +
                    "  </body>\n" +
                    "</html>";

            message.setContent(htmlContent, "text/html");

            // Envia o email
            Transport.send(message);

            System.out.println("Email enviado com sucesso!");


        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}
