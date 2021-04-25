package io.github.vitor0x5.domains.user.services.authentication;

import io.github.vitor0x5.domains.user.dto.CredentialsDTO;
import io.github.vitor0x5.domains.user.dto.TokenDTO;
import io.github.vitor0x5.domains.user.entities.AppUser;
import io.github.vitor0x5.domains.user.repositories.UsersRepository;
import io.github.vitor0x5.domains.user.repositories.implementations.UsersJpaRepository;
import io.github.vitor0x5.shared.encoder.Encoder;
import io.github.vitor0x5.shared.errors.types.LoginException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class UserAuthenticateService implements UserDetailsService {

    private final UsersRepository usersRepository;
    private final Encoder encoder;
    private final JwtService jwtService;

    public UserAuthenticateService(UsersJpaRepository repository,
                                   JwtService jwtService,
                                   @Qualifier(value = "encoder") Encoder encoder) {
        this.usersRepository = repository;
        this.jwtService = jwtService;
        this.encoder = encoder;
    }

    public TokenDTO authenticate(@RequestBody CredentialsDTO credentials){
        UserDetails user;
        try {
            user = loadUserByUsername(credentials.getEmail());
        } catch (UsernameNotFoundException ex) {
            throw new LoginException(LoginException.userNotFound);
        }

        if(encoder.matches(credentials.getPassword(), user.getPassword())) {

            return jwtService.generateToken(user.getUsername());
        } else {
            throw new LoginException(LoginException.userNotFound);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<AppUser> findUser = usersRepository.findByEmail(email);

        if(findUser.isEmpty()) {
            throw new UsernameNotFoundException(LoginException.userNotFound);
        }

        AppUser appUser = findUser.get();

        String[] roles = {"USER"};

        return User
                .builder()
                .username(appUser.getEmail())
                .password(appUser.getEncryptedPassword())
                .roles(roles)
                .build();
    }
}
