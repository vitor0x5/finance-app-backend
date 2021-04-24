package io.github.vitor0x5.domains.user.controllers;

import io.github.vitor0x5.domains.user.entities.AppUser;
import io.github.vitor0x5.domains.user.services.CreateUserService;
import io.github.vitor0x5.domains.user.services.DeleteUserService;
import io.github.vitor0x5.domains.user.services.ShowProfileService;
import io.github.vitor0x5.shared.errors.types.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.UUID;

@RestController()
public class UserController {

    private final ShowProfileService showProfileService;
    private final CreateUserService createUserService;
    private final DeleteUserService deleteUserService;

    public UserController(ShowProfileService showProfileService, CreateUserService createUserService, DeleteUserService deleteUserService) {
        this.showProfileService = showProfileService;
        this.createUserService = createUserService;
        this.deleteUserService = deleteUserService;
    }

    @PostMapping
    @RequestMapping("/sign/up")
    @ResponseStatus(HttpStatus.CREATED)
    public AppUser createUser(@RequestBody @Valid AppUser appUser) {
        try {
            return createUserService.execute(appUser);
        } catch (BusinessException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping(value = "/user/{id}")
    public AppUser getUser(@PathVariable("id") UUID id) {
      return showProfileService.execute(id);
    }

    @DeleteMapping(value = "/user/{id}")
    public void deleteUser(@PathVariable("id") UUID id) {
        deleteUserService.execute(id);
    }
}
