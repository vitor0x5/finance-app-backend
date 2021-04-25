package io.github.vitor0x5.domains.user.repositories;

import io.github.vitor0x5.domains.user.entities.AppUser;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

public interface UsersRepository {

    public Optional<AppUser> findByEmail(String email);

    public Optional<AppUser> findById(UUID id);

    public AppUser save(AppUser user);

    public void delete(AppUser user);
}
