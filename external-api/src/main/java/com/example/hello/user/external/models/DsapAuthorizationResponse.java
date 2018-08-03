package com.example.hello.user.external.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

@JsonDeserialize
public class DsapAuthorizationResponse {

    @JsonProperty("customerId")
    public String customerId;

    @JsonProperty("email")
    public String email;

    @JsonProperty("number")
    public String number;

    @JsonProperty("program")
    public List<Program> program;

    @JsonProperty("headers")
    public CustomHeader headers;

    @JsonCreator
    public DsapAuthorizationResponse() {
    }

    @Override
    public String toString() {
        return "DsapAuthorizationResponse{" +
                "customerId='" + customerId + '\'' +
                ", email='" + email + '\'' +
                ", number='" + number + '\'' +
                ", program=" + program +
                ", headers=" + headers +
                '}';
    }

    @JsonDeserialize
    public class CustomHeader {
        @JsonProperty("authorization")
        public String authorization;

        @JsonProperty("host")
        public String host;

        @JsonCreator
        public CustomHeader() {
        }

        @Override
        public String toString() {
            return "CustomHeader{" +
                    "authorization='" + authorization + '\'' +
                    ", host='" + host + '\'' +
                    '}';
        }
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
