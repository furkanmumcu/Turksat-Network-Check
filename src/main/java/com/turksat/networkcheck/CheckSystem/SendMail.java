package com.turksat.networkcheck.CheckSystem;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
@SuppressWarnings("Duplicates")


public class SendMail {

    private final String username = "turksatdeneme@hotmail.com";
    private final String password = "fff123FFF";

    /*
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        //props.put("mail.smtp.host", "smtp.gmail.com");
        //props.put("mail.smtp.port", "587");
        props.put("mail.smtp.host", "smtp.live.com");
        props.put("mail.smtp.port", "25");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("turksatdeneme@hotmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("furkanmumcu2@gmail.com"));
            message.setSubject("Testing Subject");
            message.setText("Dear Mail Crawler,"
                    + "\n\n No spam to my email, please!");

            Transport.send(message);

            System.out.println("Done");

        }
        catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    */

    //////////////////////////

    public void send(String toSend, String subject, String body){
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.live.com");
        props.put("mail.smtp.port", "25");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toSend));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

            System.out.println("Mail Sent");

        }
        catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}