package com.example.backend.login.restAPI;

import com.example.backend.login.Exception.UserErrorResponse;
import com.example.backend.login.Exception.UserNotFoundException;
import com.example.backend.login.model.User;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Binh
 * Date : 7/12/2023 - 10:26 PM
 * Description :
 */
@RestController
@RequestMapping("/api")
public class UserRestController {
    private List<User> users;

    @PostConstruct
    private void loadData(){
        users = new ArrayList<>();

        // test api
        users.add(new User("blubin","123"));
    }
    @GetMapping("/users")
    public List<User> getallUser(){
        return users;
    }

    @GetMapping("/users/{userId}")
    public User getUser(@PathVariable int userId){
        if(userId>= users.size() || userId < 0){
            throw new UserNotFoundException("User is not found -"+ userId);
        }
        return users.get(userId);

    }
    // Exception 404-NOT FOUND
    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> HandleException(UserNotFoundException exc){
        UserErrorResponse error = new UserErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
    // Exception 400 - BAD REQUEST
    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> handleException(Exception ex){
        UserErrorResponse error = new UserErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(ex.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}
