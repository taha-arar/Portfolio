package portfolio.me.controller;

import org.apache.pdfbox.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import portfolio.me.model.EmailRequest;
import portfolio.me.service.EmailService;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


@Controller

public class ResumeController {

     private final EmailService emailService;

     private final JavaMailSender mailSender;


    @Value("${email.subject}")
    private String emailSubject;

    @Value("${email.to}")
    private String emailTo;

    public ResumeController(EmailService emailService, JavaMailSender mailSender) {
        this.emailService = emailService;
        this.mailSender = mailSender;
    }

    @RequestMapping("/")
    public String index() throws IOException {
        return "index";

    }

    @GetMapping("/downloadCV")
    public ResponseEntity<byte[]> downloadPdf() throws IOException {
        String pdfUrl = "https://firebasestorage.googleapis.com/v0/b/taha-s-portfolio.appspot.com/o/Taha%20Arar%20CV%20October-2022.pdf?alt=media&token=021c12a6-229e-4257-bace-52d7de29d98e"; // replace with your PDF file URL

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        InputStream inputStream = null;
        try {
            URL url = new URL(pdfUrl);
            inputStream = url.openStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "Taha Arar CV October-2022.pdf");
        headers.setContentLength(outputStream.toByteArray().length);

        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }



    @PostMapping("/sendEmail")
    public ResponseEntity<Void> submitContactUsForm(@RequestBody EmailRequest request) {
        try {
            String emailText = "Name: " + request.getSubject() + "\n" +
                    "Email: " + request.getFrom() + "\n" +
                    "Message: " + request.getText();

            System.out.println("*************"+emailText);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(request.getFrom());
            message.setTo(emailTo);
            message.setSubject(emailSubject);
            System.out.println("All good ******************");
            message.setText(emailText);

            mailSender.send(message);
            System.out.println("email send...");

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/print/pdf")
    public ResponseEntity<byte[]> printPdf() throws IOException {
        String pdfUrl = "https://firebasestorage.googleapis.com/v0/b/taha-s-portfolio.appspot.com/o/Taha%20Arar%20CV%20October-2022.pdf?alt=media&token=14c89fab-b13a-49c1-8468-a19b47553496";
        URL url = new URL(pdfUrl);
        InputStream in = url.openStream();
        byte[] pdfBytes = IOUtils.toByteArray(in);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.builder("inline").build());
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);


        return response;
    }

}




