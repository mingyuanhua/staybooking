package com.hmy.staybooking.controller;

import com.hmy.staybooking.model.Token;
import com.hmy.staybooking.model.User;
import com.hmy.staybooking.model.UserRole;
import com.hmy.staybooking.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    // implement two separate API for guest and host
    @PostMapping("/authenticate/guest")
    public Token authenticateGuest(@RequestBody User user) {
        return authenticationService.authenticate(user, UserRole.ROLE_GUEST);
    }

    @PostMapping("/authenticate/host")
    public Token authenticateHost(@RequestBody User user) {
        return authenticationService.authenticate(user, UserRole.ROLE_HOST);
    }
}

