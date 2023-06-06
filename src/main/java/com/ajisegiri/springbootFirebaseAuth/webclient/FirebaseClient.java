package com.ajisegiri.springbootFirebaseAuth.webclient;

import com.ajisegiri.springbootFirebaseAuth.dto.LoginPayload;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.net.URI;

@HttpExchange(accept = "application/json", contentType = "application/json")
public interface FirebaseClient {

    @PostExchange
    LoginPayload signIn(URI uri, @RequestBody Object payload);


}
