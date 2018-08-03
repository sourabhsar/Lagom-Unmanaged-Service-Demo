package com.example.hello.user.api;

import com.example.hello.user.models.AuthorizationRequest;
import com.example.hello.user.models.AuthorizationResponse;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.api.transport.Method;
import com.lightbend.lagom.javadsl.server.HeaderServiceCall;

import static com.lightbend.lagom.javadsl.api.Service.named;
import static com.lightbend.lagom.javadsl.api.Service.restCall;

public interface UserService extends Service{

    ServiceCall<AuthorizationRequest, AuthorizationResponse> authenticate();

    @Override
    default Descriptor descriptor(){
        return named("authenticate").withCalls(
                restCall(Method.POST,"/authenticate",this::authenticate))
                .withAutoAcl(true);
    }

}
