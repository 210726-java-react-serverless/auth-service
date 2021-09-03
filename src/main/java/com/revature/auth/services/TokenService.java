package com.revature.auth.services;

import com.revature.auth.dto.Principal;
import com.revature.auth.util.TokenGenerator;
import com.revature.auth.util.TokenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private TokenGenerator tokenGenerator;
    private TokenValidator tokenValidator;

    @Autowired
    public TokenService(TokenGenerator tokenGenerator, TokenValidator tokenValidator) {
        this.tokenGenerator = tokenGenerator;
        this.tokenValidator = tokenValidator;
    }

    public String generateToken(Principal subject) {
        return tokenGenerator.createToken(subject);
    }

    public boolean isTokenValid(String token) {
        return tokenValidator.parseToken(token) != null;
    }

    public String getAuthorities(String headerValue) {

        if (headerValue == null || headerValue.trim().equalsIgnoreCase("")) {
            throw new RuntimeException("Provided token was blank or null");
        }

        String token = headerValue.replaceAll("Bearer ", "");

        Principal principal = tokenValidator.parseToken(token);

        if (principal == null) {
            throw new RuntimeException("Non-valid token provided");
        }

        return principal.getUsername();
    }

}
