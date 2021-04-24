package io.github.vitor0x5.domains.user.controllers;

import io.github.vitor0x5.domains.user.dto.CredentialsDTO;
import io.github.vitor0x5.domains.user.dto.TokenDTO;
import io.github.vitor0x5.domains.user.entities.AppUser;
import io.github.vitor0x5.domains.user.services.authentication.UserAuthenticateService;
import io.github.vitor0x5.shared.errors.types.LoginException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController()
@RequestMapping("/")
public class SessionController {

    private final UserAuthenticateService userAuthenticateService;

    public SessionController(UserAuthenticateService userAuthenticateService) {
        this.userAuthenticateService = userAuthenticateService;
    }

    @PostMapping
    @RequestMapping("sign/in")
    @ResponseStatus(HttpStatus.OK)
    public TokenDTO authenticate(@RequestBody @Valid CredentialsDTO credentials) {
        try{
            return userAuthenticateService.authenticate(credentials);
        } catch (LoginException ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ex.getMessage());
        }
    }

}
