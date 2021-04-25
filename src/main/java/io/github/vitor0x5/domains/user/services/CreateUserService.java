package io.github.vitor0x5.domains.user.services;

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
    public AppUser execute(AppUser appUser) throws BusinessException{
        if(usersRepository.findByEmail(appUser.getEmail()).isPresent()) {
            throw new BusinessException(
                    BusinessException.emailAlreadyUsed);
        }
        if(appUser.getPassword() == null){
            throw  new BusinessException(BusinessException.emptyPassword);
        }
        appUser.setEncryptedPassword(encoder.encode(appUser.getPassword()));
        return usersRepository.save(appUser);
    }
}
