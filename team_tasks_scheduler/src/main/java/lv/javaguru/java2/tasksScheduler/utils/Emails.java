package lv.javaguru.java2.tasksScheduler.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Emails {
    public static boolean sendEmail(String from, String password, String host, String port,
                                    String protocol, String to, String subject, String body,
                                    boolean bodyHTML) {

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        if (protocol.equals("tls"))
            properties.put("mail.smtp.starttls.enable", "true");
        else
            properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            if (bodyHTML)
                message.setContent(body,"text/html; charset=utf-8");
            else
                message.setText(body);

            Transport.send(message);
        } catch (MessagingException mex) {
            return false;
        }
        return true;
    }
}
