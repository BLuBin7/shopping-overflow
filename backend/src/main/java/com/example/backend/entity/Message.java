//package com.example.backend.entity;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//public class Message {
//    @Id
//    private String id;
//
//    private String text;
//    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
//    @JoinColumn(name="user_name")
//    private User user_name;
//
//    public User getUser_name() {
//        return user_name;
//    }
//
//    public void setUser_name(User user_name) {
//        this.user_name = user_name;
//    }
//
//    public String getText() {
//        return text;
//    }
//
//    public void setText(String text) {
//        this.text = text;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//
//
//}
