package com.hmy.staybooking.model;

// we don't need to mark Token class as Entity since we don't store it in database
public class Token {
    private final String token;

    public Token(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}

