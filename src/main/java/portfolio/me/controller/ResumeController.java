package portfolio.me.controller;

import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import portfolio.me.model.EmailRequest;
import portfolio.me.service.EmailService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


@Controller

public class ResumeController {

     private final EmailService emailService;

    public ResumeController(EmailService emailService) {
        this.emailService = emailService;
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
    public ResponseEntity<?> sendEmail(EmailRequest emailRequest) {
        String from = emailRequest.getFrom();
        String subject = emailRequest.getSubject();
        String text = emailRequest.getText();
        System.out.println("***********"+from+subject+text);
        this.emailService.sendEmail(from, subject, text);
        return ResponseEntity.ok().build();
    }




}