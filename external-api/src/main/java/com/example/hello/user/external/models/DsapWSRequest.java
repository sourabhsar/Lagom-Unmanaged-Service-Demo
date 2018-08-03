package com.example.hello.user.external.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize
public class DsapWSRequest {

    @JsonProperty("username")
    public String username;

    @JsonProperty("password")
    public String password;

    @JsonCreator
    public DsapWSRequest() {
    }

    @Override
    public String toString() {
        return "DsapWSRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
