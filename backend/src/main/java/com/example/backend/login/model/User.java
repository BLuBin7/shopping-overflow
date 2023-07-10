package com.example.backend.login.model;

import jakarta.persistence.*;

/**
 * Created by Binh
 * Date : 6/22/2023 - 10:26 PM
 * Description :
 */
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id")
    private int _id;
    @Column(name = "_username")
    private String _username;
    @Column(name = "_password")
    private String _password;

    public User() {}

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_username() {
        return _username;
    }

    public void set_username(String _username) {
        this._username = _username;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }
}
