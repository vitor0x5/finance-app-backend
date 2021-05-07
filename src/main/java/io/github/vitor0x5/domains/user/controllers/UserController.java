package io.github.vitor0x5.domains.user.controllers;

import io.github.vitor0x5.domains.user.dto.SignUpDTO;
import io.github.vitor0x5.domains.user.dto.UserDataDTO;
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

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDataDTO createUser(@RequestBody @Valid SignUpDTO user) {
        return createUserService.execute(user);
    }

    @GetMapping(value = "/me/profile")
    public UserDataDTO getUser(@RequestAttribute("userEmail") String userEmail) {
      return showProfileService.execute(userEmail);
    }

    @DeleteMapping(value = "/users/{id}")
    public void deleteUser(@PathVariable("id") UUID id) {
        deleteUserService.execute(id);
    }
}
