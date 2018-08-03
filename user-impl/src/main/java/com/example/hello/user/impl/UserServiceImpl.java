package com.example.hello.user.impl;

import akka.japi.Pair;
import com.example.hello.user.api.UserService;
import com.example.hello.user.external.api.DsapExternalService;
import com.example.hello.user.external.models.DsapAuthorizationResponse;
import com.example.hello.user.external.models.DsapWSRequest;
import com.example.hello.user.mapper.ResponseMapper;
import com.example.hello.user.models.AuthorizationRequest;
import com.example.hello.user.models.AuthorizationResponse;
import com.google.inject.Inject;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.api.transport.MessageProtocol;
import com.lightbend.lagom.javadsl.api.transport.Method;
import com.lightbend.lagom.javadsl.api.transport.RequestHeader;
import com.lightbend.lagom.javadsl.api.transport.ResponseHeader;
import com.lightbend.lagom.javadsl.server.HeaderServiceCall;
import org.pcollections.HashTreePMap;
import org.pcollections.TreePVector;

import java.net.URI;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

public class UserServiceImpl implements UserService {

    DsapExternalService dsapExternalService;

    ResponseMapper responseMapper;

    //todo: remove response mapper injection
    @Inject
    public UserServiceImpl(DsapExternalService dsapExternalService, ResponseMapper responseMapper) {
        this.dsapExternalService = dsapExternalService;
        this.responseMapper = responseMapper;
    }

    public CompletionStage<DsapAuthorizationResponse> hitAPI(AuthorizationRequest request) {
        DsapWSRequest dsapWSRequest = new DsapWSRequest();
        dsapWSRequest.username = request.username;
        dsapWSRequest.password = request.password;
        CompletionStage<DsapAuthorizationResponse> dsapresponse = dsapExternalService
                .authenticate()
                .handleRequestHeader(requestHeader -> {
                    requestHeader.withHeader("Authorization","authorizationHeader");
                    System.out.println("My Headers>>>>>>>> " + requestHeader);
                    return requestHeader;
                })
                .handleResponseHeader((responseHeader,b) -> {
                    System.out.println("RESPonse Header >>>>>>> : "+responseHeader);
                    return b;
                })
                .invoke(dsapWSRequest);
        return dsapresponse;

    }

    private static Pair<ResponseHeader, DsapAuthorizationResponse> concatHeader(ResponseHeader responseHeader,
                                                                                DsapAuthorizationResponse response) {
        return Pair.create(responseHeader, response);
    }


    @Override
    public ServiceCall<AuthorizationRequest, AuthorizationResponse> authenticate() {
        return request ->
        {
            CompletionStage<DsapAuthorizationResponse> dsapAuthorizationResponse = hitAPI(request);

            System.out.println("Before mapping");
            return dsapAuthorizationResponse.thenApply(r -> {
                System.out.println("Response>>>>>>> : " +r);
                return r;
            }).thenApply(ResponseMapper::getResponse);
        };
    }

}
