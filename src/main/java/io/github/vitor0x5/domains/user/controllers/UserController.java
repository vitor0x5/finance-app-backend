package io.github.vitor0x5.domains.user.controllers;

import io.github.vitor0x5.domains.user.models.User;
import io.github.vitor0x5.domains.user.repositories.UserRepository;
import io.github.vitor0x5.domains.user.services.CreateUserService;
import io.github.vitor0x5.domains.user.services.DeleteUserService;
import io.github.vitor0x5.domains.user.services.ShowProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController()
@RequestMapping("/user")
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
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody @Valid User user) {
        return createUserService.execute(user);
    }

    @GetMapping(value = "/{id}")
    public User getUser(@PathVariable("id") UUID id) {
      return showProfileService.execute(id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable("id") UUID id) {
        deleteUserService.execute(id);
    }
}
