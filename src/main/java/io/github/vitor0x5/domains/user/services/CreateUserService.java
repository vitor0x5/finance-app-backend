package io.github.vitor0x5.domains.user.services;

import io.github.vitor0x5.domains.user.entities.AppUser;
import io.github.vitor0x5.domains.user.repositories.UserRepository;
import io.github.vitor0x5.shared.errors.types.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateUserService {

    private final UserRepository repository;

    public CreateUserService(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public AppUser execute(AppUser appUser) throws BusinessException {
        if(repository.findByEmail(appUser.getEmail()) != null) {
            throw new BusinessException(
                    BusinessException.emailAlreadyUsed);
        }
        if(appUser.getPassword() == null){
            throw  new BusinessException(BusinessException.emptyPassword);
        }
        appUser.encodePassword(appUser.getPassword());
        return repository.save(appUser);
    }
}
