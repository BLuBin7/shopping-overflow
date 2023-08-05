//package com.example.backend.service;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
///**
// * Created by Binh
// * Date : 7/30/2023 - 9:13 PM
// * Description :
// */
//@Service
//public class EmailSenderService {
//    @Autowired
//    private JavaMailSender mailSender;
//
//    public void sendEmail(String toEmail, String subject,String body){
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("9thungphi@gmail.com");
//        message.setTo(toEmail);
//        message.setText(body);
//        message.setSubject(subject);
//
//        mailSender.send(message);
//        System.out.println("Success");
//    }
//}
