package com.tourmanager.TourManager.service;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@Component
@PropertySource("application.properties")
public class MailService {
    static void sendEmail() throws IOException, MessagingException {
        StringBuilder buildMyMessage =new StringBuilder();

        buildMyMessage.append("message text");

        String user = "sherbakoff.s2014@yandex.ru";
        String password = "bsshexwskxdgchxk";

        String to = "sherbakoff.s2014@yandex.ru";
        String smtpHost = "smtp.yandex.ru";
        Integer port = 465;

        Properties prop = new Properties();
        prop.put("mail.smtp.host", smtpHost);
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.port", port);
        prop.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(user));

        InternetAddress[] addresses = {new InternetAddress(to)};

        message.setRecipients(Message.RecipientType.TO, addresses);
        message.setSubject("Заявка! ");
        message.setSentDate(new Date());
        message.setText(buildMyMessage.toString());

        Transport.send(message);



    }
}
