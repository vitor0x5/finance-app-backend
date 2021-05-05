package io.github.vitor0x5.domains.outcome.repositories.implementations;

import io.github.vitor0x5.domains.income.entities.Income;
import io.github.vitor0x5.domains.outcome.entities.Outcome;
import io.github.vitor0x5.domains.outcome.repositories.OutcomesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OutcomesJpaRepository extends OutcomesRepository, JpaRepository<Outcome, UUID> {
    Optional<Outcome> findByUserId(UUID userId);
}
