package com.example.hello.user.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

@JsonDeserialize
public class AuthorizationResponse {

    @JsonProperty("customerId")
    public String customerId;

    @JsonProperty("email")
    public String email;

    @JsonProperty("number")
    public String number;

    @JsonProperty("program")
    public List<Program> program;

    @JsonCreator
    public AuthorizationResponse() {
    }

    @Override
    public String toString() {
        return "AuthorizationResponse{" +
                "customerId='" + customerId + '\'' +
                ", email='" + email + '\'' +
                ", number='" + number + '\'' +
                ", program=" + program +
                '}';
    }

    @JsonDeserialize
    public class Program {
        @JsonProperty("code")
        public String code;

        @JsonProperty("description")
        public String description;

        @JsonProperty("lookup")
        public String lookup;

        @JsonCreator
        public Program() {
        }

        @Override
        public String toString() {
            return "Program{" +
                    "code='" + code + '\'' +
                    ", description='" + description + '\'' +
                    ", lookup='" + lookup + '\'' +
                    '}';
        }
    }
}
