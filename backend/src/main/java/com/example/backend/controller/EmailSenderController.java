//package com.example.backend.controller;
//
//import com.example.backend.entity.EmailRequest;
//import com.example.backend.util.JwtUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.UUID;
//
///**
// * Created by Binh
// * Date : 7/30/2023 - 9:20 PM
// * Description :
// */
//@RestController
//public class EmailSenderController {
////    @Autowired
////    public EmailSenderService emailSenderService;
////    @GetMapping("/sendemail")
////    public void sendEmail(){
////        emailSenderService.sendEmail("haihaha078@gmail.com","Subject","Non");
////    }
//        private final JavaMailSender mailSender;
//        private final JwtUtil jwtUtil;
//
//    public EmailSenderController(JavaMailSender mailSender, JwtUtil jwtUtil) {
//        this.mailSender = mailSender;
//        this.jwtUtil = jwtUtil;
//    }
//
//    @PostMapping({"/forgo"})
//    public void sendPasswordResetEmail(@RequestBody EmailRequest emailRequest) {
//        String toEmail = emailRequest.getEmail();
//
//        String resetToken = generateResetToken();
//
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("9thungphi@gmail.com");
//        message.setTo(toEmail);
//        message.setSubject("Password Reset Request");
//        message.setText("Click on the link to reset your password: " +
//                "http://localhost:3000/resetpassword?");
//
//        mailSender.send(message);
//        System.out.println("Password reset email sent successfully.");
//    }
//
//    // Method to generate a unique reset token using UUID
//    private String generateResetToken() {
//        return UUID.randomUUID().toString();
//    }
//}
