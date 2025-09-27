package com.example.demo.Service;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.File;
import java.io.InputStream;

@Document
public interface EmailService {
    // send email to single person
    void SendEmail(String to, String subject, String message);
    // send email  to multiple person
    void SendEmail(String []to, String subject, String message);
    //void sendEmailWithHtml
    void sendEmailWithHtml(String to, String subject, String htmlContent);

    // void send email with file

    void sendEmailWithFile(String to, String subject, String message, File file);

    void sendEmailWithFile(String to, String subject, String message, InputStream is);


}
