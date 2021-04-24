package io.github.vitor0x5.domains.user.services.authentication;

import io.github.vitor0x5.domains.user.dto.CredentialsDTO;
import io.github.vitor0x5.domains.user.dto.TokenDTO;
import io.github.vitor0x5.domains.user.entities.AppUser;
import io.github.vitor0x5.domains.user.repositories.UserRepository;
import io.github.vitor0x5.shared.encoder.Encoder;
import io.github.vitor0x5.shared.errors.types.LoginException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserAuthenticateService implements UserDetailsService {

    private final UserRepository repository;
    private final Encoder encoder;
    private final JwtService jwtService;

    public UserAuthenticateService(UserRepository repository, JwtService jwtService) {
        this.repository = repository;
        this.jwtService = jwtService;
        this.encoder = new Encoder();
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
        AppUser appUser = repository.findByEmail(email);

        if(appUser == null) {
            throw new UsernameNotFoundException(LoginException.userNotFound);
        }

        String[] roles = {"USER"};

        return User
                .builder()
                .username(appUser.getEmail())
                .password(appUser.getEncryptedPassword())
                .roles(roles)
                .build();
    }
}
