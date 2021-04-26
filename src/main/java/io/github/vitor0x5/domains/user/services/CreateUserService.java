package io.github.vitor0x5.domains.user.services;

import io.github.vitor0x5.domains.user.dto.SignUpDTO;
import io.github.vitor0x5.domains.user.dto.UserDataDTO;
import io.github.vitor0x5.domains.user.entities.AppUser;
import io.github.vitor0x5.domains.user.repositories.UsersRepository;
import io.github.vitor0x5.shared.encoder.Encoder;
import io.github.vitor0x5.shared.errors.types.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateUserService {

    private final UsersRepository usersRepository;
    private final Encoder encoder;

    public CreateUserService(UsersRepository repository, Encoder encoder) {
        this.usersRepository = repository;
        this.encoder = encoder;
    }

    @Transactional
    public UserDataDTO execute(SignUpDTO userData) throws BusinessException{
        if(usersRepository.findByEmail(userData.getEmail()).isPresent()) {
            throw new BusinessException(
                    BusinessException.emailAlreadyUsed);
        }

        AppUser user = SignUpDTO.toAppUser(userData);
        user.setPassword(encoder.encode(userData.getPassword()));
        user = usersRepository.save(user);

        return new UserDataDTO(user.getName(), user.getEmail());
    }
}
