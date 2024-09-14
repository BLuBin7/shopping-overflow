//package com.example.backend.controller;
//
//import com.example.backend.dao.MessageDao;
//import com.example.backend.dao.UserDao;
//import com.example.backend.entity.Message;
//import com.example.backend.entity.User;
//import com.example.backend.service.JwtService;
//import com.example.backend.service.MessageService;
//import com.example.backend.util.JwtUtil;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//public class MessageController {
//    @Autowired
//    private MessageDao messageDao;
//    @Autowired
//    private UserDao userDao;
//    @Autowired
//    private MessageService messageService;
//    @Autowired
//    private JwtService jwtService;
//    @Autowired
//    private JwtUtil jwtUtil;
//    @PostMapping(value = "/addMessage/{user_id}",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
//    public Message addMessage(@PathVariable("user_id") String user_name,
//                              @RequestPart("message") String messageJson,
//                                HttpServletRequest request){
//        Message message = null;
//        try {
//            message = (new ObjectMapper()).readValue(messageJson, Message.class);
//
//            List<Message> messageList = new ArrayList<>();
//            messageList.add(message);
//
//            String jwtToken = request.getHeader("Authorization");
//            if (jwtToken != null && jwtToken.startsWith("Bearer ")) {
//                jwtToken = jwtToken.substring(7);
//            }
//            System.out.println(jwtToken);
//            user_name = jwtUtil.getUsernameFromToken(jwtToken);
//
//
//            jwtService.loadUserByUsername(user_name);
//            User authenticatedUser = userDao.findById(user_name).orElse(null);
//            if (authenticatedUser != null) {
//                message.setUser_name(authenticatedUser);
//            }
//
//            userDao.findById(user_name).get().getMessageList().add(message);
//            System.out.println(messageList);
//
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//        return messageService.AddMessage(message);
//    }
////    @GetMapping("/getallMessage/{user_name}")
////    public List<Message> messageList (@PathVariable("user_name") String user_name){
//////        User user = userDao.findById(user_name).get();
////        User user = messageDao.findById(user_name).get().getUser_name();
////        Iterable<Message> list = messageDao.findAll();
////        List<Message> listFromUser = user.getMessageList();
////
////    }
//}
