package com.ajisegiri.springbootFirebaseAuth.service;

import com.ajisegiri.springbootFirebaseAuth.dto.LoginDto;
import com.ajisegiri.springbootFirebaseAuth.dto.LoginPayload;
import com.ajisegiri.springbootFirebaseAuth.model.User;
import com.ajisegiri.springbootFirebaseAuth.repo.UserRepository;
import com.ajisegiri.springbootFirebaseAuth.webclient.FirebaseClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AuthService {
    private final FirebaseAuth firebaseAuth;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final FirebaseClient firebaseClient;
    private static final String SIGN_IN ="https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword";
    @Value("${firebase.apikey}")
    private String apikey;

    @SneakyThrows
    @Transactional
    public LoginPayload createUser(User user){
        var password=user.getPassword();
        var body=new UserRecord.CreateRequest();
        body.setPassword(user.getPassword());
        body.setEmail(user.getEmail());
        body.setEmailVerified(true);
        var response=firebaseAuth.createUser(body);

        user.setFireBaseUid(response.getUid());
        user.setPassword(passwordEncoder.encode(password));
        try {
            userRepository.save(user);
            firebaseAuth.setCustomUserClaims(response.getUid(),Map.of("custom_claims", List.of(user.getRole().toString())));
        } catch (Exception exception){
            firebaseAuth.deleteUser(response.getUid());
            throw new IllegalStateException(exception.getMessage());
        }
        return getToken(user.getEmail(),password);
    }

    public LoginPayload login(LoginDto loginDto){
        return getToken(loginDto.email(),loginDto.password());
    }

    private LoginPayload getToken(String email, String password){
        Map<String, Object> payload = new HashMap<>();
        payload.put("email", email);
        payload.put("password", password);
        payload.put("returnSecureToken", true);

        var uri= UriComponentsBuilder.
                fromUriString(SIGN_IN).queryParam("key",apikey)
                .build()
                .toUri();

        return firebaseClient.signIn(uri,payload);

    }

}
