package com.revature.auth.util;

import com.revature.auth.dto.Principal;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TokenValidator {

    private JwtConfig config;

    @Autowired
    public TokenValidator(JwtConfig config) {
        this.config = config;
    }

    public Principal parseToken(String token) {

        Claims claims = Jwts.parser()
                .setSigningKey(config.getSigningKey())
                .parseClaimsJws(token)
                .getBody();

        return new Principal(claims.getId(), claims.getSubject());

    }
}
