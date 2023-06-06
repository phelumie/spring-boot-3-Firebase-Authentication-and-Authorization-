package com.ajisegiri.springbootFirebaseAuth.service;

import com.ajisegiri.springbootFirebaseAuth.model.User;
import com.ajisegiri.springbootFirebaseAuth.repo.UserRepository;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public List<User> findAll(){
        return userRepository.findAll();
    }

}
