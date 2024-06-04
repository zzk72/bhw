package com.example.bhw.Bean;

import jakarta.ejb.Stateless;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

@Stateless
public class EmailServiceBean {

    private Properties properties = new Properties();

    public EmailServiceBean() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("email.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find email.properties");
                return;
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void sendEmail(String toEmail, String subject, String body) {
        final String fromEmail = properties.getProperty("mail.from");
        final String password = properties.getProperty("mail.password");

        Properties smtpProperties = new Properties();
        smtpProperties.put("mail.smtp.host", properties.getProperty("mail.smtp.host"));
        smtpProperties.put("mail.smtp.port", properties.getProperty("mail.smtp.port"));
        smtpProperties.put("mail.smtp.auth", properties.getProperty("mail.smtp.auth"));
        smtpProperties.put("mail.smtp.starttls.enable", properties.getProperty("mail.smtp.starttls.enable"));

        Session session = Session.getInstance(smtpProperties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

            System.out.println("Email sent successfully to " + toEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public String generateVerificationCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }
}
