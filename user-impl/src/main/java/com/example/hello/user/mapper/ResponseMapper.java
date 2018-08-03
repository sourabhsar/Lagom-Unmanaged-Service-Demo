package com.example.hello.user.mapper;

import com.example.hello.user.external.models.DsapAuthorizationResponse;
import com.example.hello.user.external.models.UserData;
import com.example.hello.user.models.AuthorizationResponse;
import com.example.hello.user.models.UserResponse;

public class ResponseMapper {

    public static AuthorizationResponse getResponse(DsapAuthorizationResponse dsapAuthorizationResponse) {
        AuthorizationResponse authorizationResponse = new AuthorizationResponse();
        authorizationResponse.customerId = dsapAuthorizationResponse.customerId;
        authorizationResponse.email = dsapAuthorizationResponse.email;
        authorizationResponse.number = dsapAuthorizationResponse.number;
        return authorizationResponse;
    }
}
