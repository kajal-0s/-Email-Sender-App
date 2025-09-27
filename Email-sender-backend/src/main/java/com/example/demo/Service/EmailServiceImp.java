package com.example.demo.Service;
import com.example.demo.repository.EmailMessagesRepo;
import helper.EmailRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EmailServiceImp implements EmailService {
    private JavaMailSender mailSender;
    @Autowired
    private EmailMessagesRepo emailMessagesRepo;
    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImp.class);

    public EmailServiceImp(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void SendEmail(String to, String subject, String message) {
       SimpleMailMessage simpleMailMessage= new SimpleMailMessage();
       simpleMailMessage.setTo(to);
       simpleMailMessage.setSubject(subject);
       simpleMailMessage.setText(message);
       simpleMailMessage.setFrom("sawkajal28@gmail.com");
       mailSender.send(simpleMailMessage);
       logger.info("Email has been sent...");

       EmailRequest log = new EmailRequest(to, subject, message);
       emailMessagesRepo.save(log);

       
    }

    @Override
    public void SendEmail(String[] to, String subject, String message) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        simpleMailMessage.setFrom("sawkajal28@gmail.com");
        mailSender.send(simpleMailMessage);

        for (String recipient : to) {
            EmailRequest log = new EmailRequest(recipient, subject, message);
            emailMessagesRepo.save(log);
        }
    }

    @Override
    public void sendEmailWithHtml(String to, String subject, String htmlContent) {
        MimeMessage simpleMailMessage = mailSender.createMimeMessage();
try {
    MimeMessageHelper helper = new MimeMessageHelper(simpleMailMessage, true,"UTF-8");
    helper.setTo(to);
    helper.setSubject(subject);
    helper.setFrom("sawkajal28@gmail.com");
    helper.setText(htmlContent,true);
    mailSender.send(simpleMailMessage);
    logger.info("Email has been sent...");

    EmailRequest log = new EmailRequest(to, subject, htmlContent);
    emailMessagesRepo.save(log);

} catch (MessagingException e) {
    throw new RuntimeException(e);
}
    }

    @Override
    public void sendEmailWithFile(String to, String subject, String message, File file) {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom("sawkajal28@gmail.com");
            helper.setTo(to);
            helper.setText(message);
            helper.setSubject(subject);
            FileSystemResource fileSystemResource=new FileSystemResource(file);
                    helper.addAttachment(fileSystemResource.getFilename(),file);

                    mailSender.send(mimeMessage);
                    logger.info("Email send success");

            EmailRequest log = new EmailRequest(to, subject, message + " [File Attached: " + file.getName() + "]");
            emailMessagesRepo.save(log);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public void sendEmailWithFile(String to, String subject,String message,InputStream is){
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom("sawkajal28@gmail.com");
            helper.setTo(to);
            helper.setText(message);
            helper.setSubject(subject);
            File file = new File("src/main/resources/test.png");

            // Handle IOException
            try {
                Files.copy(is, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException("Failed to copy file", e);
            }

            FileSystemResource fileSystemResource = new FileSystemResource(file);
            helper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);

            mailSender.send(mimeMessage);
            logger.info("Email send success");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
