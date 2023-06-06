package com.ajisegiri.springbootFirebaseAuth.controller;

import com.ajisegiri.springbootFirebaseAuth.dto.LoginDto;
import com.ajisegiri.springbootFirebaseAuth.dto.LoginPayload;
import com.ajisegiri.springbootFirebaseAuth.model.User;
import com.ajisegiri.springbootFirebaseAuth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("signup")
    public ResponseEntity<LoginPayload> signUp(@RequestBody User user){
        var result=authService.createUser(user);
        return ResponseEntity.ok(result);
    }


    @PostMapping("login")
    public ResponseEntity<LoginPayload> login(@RequestBody LoginDto loginDto){
        var result=authService.login(loginDto);
        return ResponseEntity.ok(result);
    }


}
