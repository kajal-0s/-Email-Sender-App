package com.example.demo;

import com.example.demo.Service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@SpringBootTest
public class EmailSenderTest {
    @Autowired
private EmailService emailService;

    @Test
    void emailSendTest(){

        System.out.println("sending email");
       emailService.SendEmail("pkgupta20796@gmail.com", "Email from spring boot", "This email is send using spring boot while create email service.");

    }
@Test
    void sendHtmlInEmail(){
        String html ="" + "<h1 style='color:red;border:1px solid red;'>Welcome to spring boot </h1>" + "";

        emailService.sendEmailWithHtml("sawkajal121@gmail.com","Email from spring boot", "This email is send using spring boot while create email service." );
    }
    @Test
    void sendEmailWithFile() {

        emailService. sendEmailWithFile("gk6094397@gmail.com","email with file","This email contains file",new File("C:\\Users\\Gulshan Kumar\\Downloads\\pexels-ayyappa-film-creations-587546-19893173.jpg"));
    }

    @Test
    void sendEmailWithStream() {
       File file =  new File("C:\\Users\\Gulshan Kumar\\Downloads\\pexels-ayyappa-film-creations-587546-19893173.jpg");
       try {
           InputStream is = new FileInputStream(file);
           emailService. sendEmailWithFile("sawkajal121@gmail.com","email with file","This email contains file",is);

       } catch (FileNotFoundException e) {
           throw new RuntimeException(e);
       }

    }
}


