package io.github.vitor0x5.domains.user.services;

import io.github.vitor0x5.domains.user.entities.AppUser;
import io.github.vitor0x5.domains.user.repositories.UserRepository;
import io.github.vitor0x5.shared.errors.types.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ShowProfileService {
    private final UserRepository repository;

    public ShowProfileService(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public AppUser execute(UUID id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(NotFoundException.userNotFound));
    }
}
