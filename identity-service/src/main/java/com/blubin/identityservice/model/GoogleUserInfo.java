package com.blubin.identityservice.model;

import java.util.Map;

public class GoogleUserInfo {
    private Map<String, Object> attributes;

    public GoogleUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public String getId(){
        return (String) attributes.get("sub");
    }

    public String getEmail(){
        return (String) attributes.get("email");
    }

    public String getName(){
        return (String) attributes.get("name");
    }
}
