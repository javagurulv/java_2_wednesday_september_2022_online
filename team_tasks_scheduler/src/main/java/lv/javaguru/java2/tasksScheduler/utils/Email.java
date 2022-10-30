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
    private String bodyHeader;
    private String body;
    private String bodyFooter;
    private boolean bodyIsHTML;

    public Email() {
    }

    public Email(String from, String password, String host, String port, String protocol,
                 String to, String subject, String bodyHeader, String body, String bodyFooter, boolean bodyIsHTML) {
        this.from = from;
        this.password = password;
        this.host = host;
        this.port = port;
        this.protocol = protocol;
        this.to = to;
        this.subject = subject;
        this.bodyHeader = bodyHeader;
        this.body = body;
        this.bodyFooter = bodyFooter;
        this.bodyIsHTML = bodyIsHTML;
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

    public String getBodyHeader() {
        return bodyHeader;
    }

    public void setBodyHeader(String bodyHeader) {
        this.bodyHeader = bodyHeader;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBodyFooter() {
        return bodyFooter;
    }

    public void setBodyFooter(String bodyFooter) {
        this.bodyFooter = bodyFooter;
    }

    public boolean isBodyIsHTML() {
        return bodyIsHTML;
    }

    public void setBodyIsHTML(boolean bodyIsHTML) {
        this.bodyIsHTML = bodyIsHTML;
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
            if (bodyIsHTML)
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
        return bodyIsHTML == email.bodyIsHTML && Objects.equals(from, email.from) && Objects.equals(password, email.password) && Objects.equals(host, email.host) && Objects.equals(port, email.port) && Objects.equals(protocol, email.protocol) && Objects.equals(to, email.to) && Objects.equals(subject, email.subject) && Objects.equals(bodyHeader, email.bodyHeader) && Objects.equals(body, email.body) && Objects.equals(bodyFooter, email.bodyFooter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, password, host, port, protocol, to, subject, bodyHeader, body, bodyFooter, bodyIsHTML);
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
                ", bodyHeader='" + bodyHeader + '\'' +
                ", body='" + body + '\'' +
                ", bodyFooter='" + bodyFooter + '\'' +
                ", bodyIsHTML=" + bodyIsHTML +
                '}';
    }
}
