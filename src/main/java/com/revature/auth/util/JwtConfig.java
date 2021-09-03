package com.revature.auth.util;

import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

@Component
public class JwtConfig {

    @Value("${jwt.secret}")
    private String secretKey;

    @Getter
    @Value("${jwt.expiration}")
    private int expiration;

    @Getter
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    @Getter
    private Key signingKey;

    @PostConstruct
    public void createSigningKey() {
        byte[] secretBytes = DatatypeConverter.parseBase64Binary(this.secretKey);
        signingKey = new SecretKeySpec(secretBytes, signatureAlgorithm.getJcaName());
    }

}
