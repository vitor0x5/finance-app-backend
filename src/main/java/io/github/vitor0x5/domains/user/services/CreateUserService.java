package io.github.vitor0x5.domains.user.services;

import io.github.vitor0x5.domains.user.models.User;
import io.github.vitor0x5.domains.user.repositories.UserRepository;
import io.github.vitor0x5.shared.errors.types.BusinessException;
import org.springframework.stereotype.Service;

@Service
public class CreateUserService {

    private final UserRepository repository;

    public CreateUserService(UserRepository repository) {
        this.repository = repository;
    }

    public User execute(User user) {
        if(repository.findByEmail(user.getEmail()) != null) {
            throw new BusinessException(
                    "Email address already used");
        }
        return repository.save(user);
    }
}
