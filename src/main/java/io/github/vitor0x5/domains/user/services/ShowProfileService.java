package io.github.vitor0x5.domains.user.services;

import io.github.vitor0x5.domains.user.dto.UserDataDTO;
import io.github.vitor0x5.domains.user.entities.AppUser;
import io.github.vitor0x5.domains.user.repositories.UsersRepository;
import io.github.vitor0x5.shared.errors.types.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ShowProfileService {
    private final UsersRepository usersRepository;
    private final ModelMapper mapper;

    public ShowProfileService(UsersRepository repository, ModelMapper mapper) {
        this.usersRepository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public UserDataDTO execute(String userEmail) {
        AppUser user = usersRepository.findByEmail(userEmail)
                .orElseThrow(() -> new NotFoundException(NotFoundException.userNotFound));

        return mapper.map(user, UserDataDTO.class);
    }
}
