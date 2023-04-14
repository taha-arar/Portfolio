package portfolio.me.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendEmail(String from, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(from);
        message.setFrom("tahaarar31@gmail.com");
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
        System.out.println("E-mail was send successfully ! ************************");

    }
}
