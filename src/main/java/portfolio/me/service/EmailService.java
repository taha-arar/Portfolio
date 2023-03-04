package portfolio.me.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String from, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("taha.arar@univ-constantine2.dz");
        message.setFrom(from);
        message.setSubject(subject);
        message.setText(text);
        System.out.println("till now all is good hhhh ");
        mailSender.send(message);
    }
}
