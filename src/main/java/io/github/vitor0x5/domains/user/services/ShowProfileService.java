package io.github.vitor0x5.domains.user.services;

import io.github.vitor0x5.domains.user.entities.AppUser;
import io.github.vitor0x5.domains.user.repositories.UsersRepository;
import io.github.vitor0x5.shared.errors.types.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ShowProfileService {
    private final UsersRepository usersRepository;

    public ShowProfileService(UsersRepository repository) {
        this.usersRepository = repository;
    }

    @Transactional
    public AppUser execute(UUID id) {
        return usersRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(NotFoundException.userNotFound));
    }
}
