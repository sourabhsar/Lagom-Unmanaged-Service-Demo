package com.example.hello.user.external.api;

import com.example.hello.user.external.models.DsapAuthorizationResponse;
import com.example.hello.user.external.models.DsapWSRequest;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.api.transport.HeaderFilter;
import com.lightbend.lagom.javadsl.api.transport.Method;
import com.lightbend.lagom.javadsl.api.transport.RequestHeader;
import com.lightbend.lagom.javadsl.api.transport.ResponseHeader;
import com.lightbend.lagom.javadsl.server.HeaderServiceCall;

public interface DsapExternalService extends Service {

    ServiceCall<DsapWSRequest, DsapAuthorizationResponse> authenticate();

    @Override
    default Descriptor descriptor() {
        return Service.named("dsap-external-service").withCalls(
                //Service.restCall(Method.POST, "/v2/accounts/login-status", this::authenticate)
                Service.restCall(Method.POST, "/post", this::authenticate)
        ).withAutoAcl(true);
    }

    public static class DsapHeaderFiler implements HeaderFilter {

        @Override
        public RequestHeader transformClientRequest(RequestHeader request) {
            System.out.println("Inside header filter>>>>>>>>");
            request.withHeader("Authorization","Basic YW5kcm9pZDpkc2Fwc2VjcmV0MTIz");
            /*request.withHeader("Content-Type","application/json");
            request.withHeader("X-Device-ID","X-Device-ID");
            request.withHeader("Accept-Language","en-US");*/
            return request;
        }

        @Override
        public RequestHeader transformServerRequest(RequestHeader request) {
            return request;
        }

        @Override
        public ResponseHeader transformServerResponse(ResponseHeader response, RequestHeader request) {
            return response;
        }

        @Override
        public ResponseHeader transformClientResponse(ResponseHeader response, RequestHeader request) {
            return response;
        }
    }

}
