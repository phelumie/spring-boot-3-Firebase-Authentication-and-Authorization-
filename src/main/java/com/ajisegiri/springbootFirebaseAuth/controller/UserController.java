package com.ajisegiri.springbootFirebaseAuth.controller;

import com.ajisegiri.springbootFirebaseAuth.model.User;
import com.ajisegiri.springbootFirebaseAuth.service.UserService;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("users")
    @RolesAllowed("USER")
    public ResponseEntity<List<User>> getUsers(Principal principal){
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

}
