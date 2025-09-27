package com.example.demo.controller;
import com.example.demo.Service.EmailService;
import helper.EmailRequest;
import helper.CustomeResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/email")
public class emailcontroller {

    private final EmailService emailService;

    public emailcontroller(EmailService emailService) {

        this.emailService = emailService;
    }

    // send email
    @PostMapping("/send")
    public ResponseEntity<CustomeResponse> sendEmail(@RequestBody EmailRequest request) {
        emailService.sendEmailWithHtml(request.getTo(), request.getSubject(), request.getMessage());

        return ResponseEntity.ok(
                CustomeResponse.builder()
                        .message("Email send successfully !!")
                        .httpStatus(HttpStatus.OK)
                        .success(true)
                        .build()
        );
    }

    // send email with file
    @PostMapping("/send-with-file")
    public ResponseEntity<CustomeResponse> sendWithFile(@RequestPart EmailRequest request, @RequestPart MultipartFile file) throws Exception {
        emailService.sendEmailWithFile(
                request.getTo(), request.getSubject(),request.getMessage(),
                file.getInputStream()
        );

        return ResponseEntity.ok(
                CustomeResponse.builder()
                        .message("Email send successfully !!")
                        .httpStatus(HttpStatus.OK)
                        .success(true)
                        .build()
        );
    }
}
