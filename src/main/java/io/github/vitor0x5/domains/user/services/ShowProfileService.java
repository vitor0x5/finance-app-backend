package io.github.vitor0x5.domains.user.services;

import io.github.vitor0x5.domains.user.dto.UserDataDTO;
import io.github.vitor0x5.domains.user.entities.AppUser;
import io.github.vitor0x5.domains.user.repositories.UsersRepository;
import io.github.vitor0x5.shared.errors.types.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

@Service
public class ShowProfileService {
    private final UsersRepository usersRepository;

    public ShowProfileService(UsersRepository repository) {
        this.usersRepository = repository;
    }

    @Transactional
    public UserDataDTO execute(UUID id) {
        Optional<AppUser> user = usersRepository.findById(id);
        if(user.isPresent()){
            return new UserDataDTO(user.get().getName(), user.get().getEmail());
        }
        throw new NotFoundException(NotFoundException.userNotFound);
    }
}
