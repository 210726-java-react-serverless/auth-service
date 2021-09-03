package com.revature.auth.controllers;

import com.revature.auth.dto.Principal;
import com.revature.auth.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/token")
public class TokenController {

    private final TokenService tokenService;

    @Autowired
    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public String generateTokenFromPrincipal(@RequestBody @Valid Principal principal) {
        return tokenService.generateToken(principal);
    }

    @GetMapping(produces = "application/json")
    public boolean validateToken(@RequestHeader("bookstore-token") String token) {
        return tokenService.isTokenValid(token);
    }

    @GetMapping(path = "/authorities")
    public String getTokenAuthorities(@RequestHeader("bookstore-token") String token) {
        return tokenService.getAuthorities(token);
    }



}
