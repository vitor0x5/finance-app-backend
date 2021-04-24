package io.github.vitor0x5.domains.user.services;

import io.github.vitor0x5.domains.user.repositories.UserRepository;
import io.github.vitor0x5.shared.errors.ApiErrors;
import io.github.vitor0x5.shared.errors.types.NotFoundException;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class DeleteUserService {
    private final UserRepository repository;

    public DeleteUserService(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void execute(UUID id) {
        repository
                .findById(id)
                .map(user -> {
                    repository.delete(user);
                    return user;
                }).orElseThrow(() ->
                new NotFoundException(NotFoundException.userNotFound));
    }
}
