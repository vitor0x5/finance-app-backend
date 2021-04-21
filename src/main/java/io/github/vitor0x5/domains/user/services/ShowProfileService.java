package io.github.vitor0x5.domains.user.services;

import io.github.vitor0x5.domains.user.models.User;
import io.github.vitor0x5.domains.user.repositories.UserRepository;
import io.github.vitor0x5.shared.errors.ApiErrors;
import io.github.vitor0x5.shared.errors.types.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class ShowProfileService {
    private final UserRepository repository;

    public ShowProfileService(UserRepository repository) {
        this.repository = repository;
    }

    public User execute(UUID id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }
}
