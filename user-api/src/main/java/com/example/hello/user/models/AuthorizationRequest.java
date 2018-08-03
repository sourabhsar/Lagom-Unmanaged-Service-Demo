package com.example.hello.user.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize
public class AuthorizationRequest {

    @JsonProperty("username")
    public String username;
    @JsonProperty("password")
    public String password;

    @JsonCreator
    public AuthorizationRequest() {
    }

    @Override
    public String toString() {
        return "AuthorizationRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
