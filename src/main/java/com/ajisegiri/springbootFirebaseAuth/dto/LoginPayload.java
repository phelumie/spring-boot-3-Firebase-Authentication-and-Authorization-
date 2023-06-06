package com.ajisegiri.springbootFirebaseAuth.dto;

public record LoginPayload(String localId,String email,
                           String displayName, String idToken,
                           boolean registered, String refreshToken,
                           String expiresIn){}
