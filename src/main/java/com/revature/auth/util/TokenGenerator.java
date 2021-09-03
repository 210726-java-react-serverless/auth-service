package com.revature.auth.util;

import com.revature.auth.dto.Principal;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenGenerator {

    private final JwtConfig jwtConfig;

    @Autowired
    public TokenGenerator(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public String createToken(Principal subject) {

        long now = System.currentTimeMillis();

        JwtBuilder tokenBuilder = Jwts.builder()
                                      .setId(subject.getId())
                                      .setSubject(subject.getUsername())
                                      .setIssuer("revature")
                                      .setIssuedAt(new Date(now))
                                      .setExpiration(new Date(now + jwtConfig.getExpiration()))
                                      .signWith(jwtConfig.getSignatureAlgorithm(), jwtConfig.getSigningKey());

        return "Bearer " + tokenBuilder.compact();

    }

}
