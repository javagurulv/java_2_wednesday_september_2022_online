package lv.javaguru.java2.tasksScheduler.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Objects;
import java.util.Properties;

public class Email {
    private String from;
    private String password;
    private String host;
    private String port;
    private String protocol;
    private String to;
    private String subject;
    private String body;
    private boolean bodyHTML;

    public Email() {
    }

    public Email(String from, String password, String host, String port, String protocol, String to, String subject, String body, boolean bodyHTML) {
        this.from = from;
        this.password = password;
        this.host = host;
        this.port = port;
        this.protocol = protocol;
        this.to = to;
        this.subject = subject;
        this.body = body;
        this.bodyHTML = bodyHTML;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isBodyHTML() {
        return bodyHTML;
    }

    public void setBodyHTML(boolean bodyHTML) {
        this.bodyHTML = bodyHTML;
    }

    public boolean send() {

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        if (this.protocol.equals("tls"))
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return bodyHTML == email.bodyHTML && Objects.equals(from, email.from) && Objects.equals(password, email.password) && Objects.equals(host, email.host) && Objects.equals(port, email.port) && Objects.equals(protocol, email.protocol) && Objects.equals(to, email.to) && Objects.equals(subject, email.subject) && Objects.equals(body, email.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, password, host, port, protocol, to, subject, body, bodyHTML);
    }

    @Override
    public String toString() {
        return "Email{" +
                "from='" + from + '\'' +
                ", password='" + password + '\'' +
                ", host='" + host + '\'' +
                ", port='" + port + '\'' +
                ", protocol='" + protocol + '\'' +
                ", to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", bodyHTML=" + bodyHTML +
                '}';
    }
}
