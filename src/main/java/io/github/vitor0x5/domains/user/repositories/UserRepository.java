package io.github.vitor0x5.domains.user.repositories;

import io.github.vitor0x5.domains.user.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<AppUser, UUID> {
    public AppUser findByEmail(String email);
}
