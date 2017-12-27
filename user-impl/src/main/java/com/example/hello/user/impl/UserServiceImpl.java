package com.example.hello.user.impl;

import akka.NotUsed;
import com.example.hello.user.api.UserService;
import com.example.hello.user.external.api.ExternalService;
import com.example.hello.user.external.models.UserData;
import com.example.hello.user.mapper.ResponseMapper;
import com.example.hello.user.models.UserResponse;
import com.google.inject.Inject;
import com.lightbend.lagom.javadsl.api.ServiceCall;

import java.util.concurrent.CompletionStage;

public class UserServiceImpl implements UserService {

    ExternalService externalService;

    ResponseMapper responseMapper;

    @Inject
    public UserServiceImpl(ExternalService externalService, ResponseMapper responseMapper) {
        this.externalService = externalService;
        this.responseMapper = responseMapper;
    }

    public CompletionStage<UserData> hitAPI() {
        CompletionStage<UserData> userData = externalService
                .getUser()
                .invoke();
        return userData;

    }

    @Override
    public ServiceCall<NotUsed, UserResponse> helloUser() {
        return request ->
        {
            CompletionStage<UserData> userData = hitAPI();
            return userData.thenApply(
                    userInfo ->
                    {
                        UserResponse userResponse = responseMapper.getResponse(userInfo);
                        return userResponse;
                    }
            );
        };
    }

}
