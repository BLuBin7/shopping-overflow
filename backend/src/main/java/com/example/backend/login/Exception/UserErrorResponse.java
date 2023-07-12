package com.example.backend.login.Exception;

import com.example.backend.login.model.User;

/**
 * Created by Binh
 * Date : 7/12/2023 - 10:23 PM
 * Description :
 */
public class UserErrorResponse {
    private int status;
    private String message;
    private long timeStamp;

    public UserErrorResponse(){}

    public UserErrorResponse(int status, String message, long timeStamp) {
        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
