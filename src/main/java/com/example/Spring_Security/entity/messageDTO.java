package com.example.Spring_Security.entity;

import lombok.Data;

@Data
public class messageDTO {

    private String message;

    // Getter & Setter
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
