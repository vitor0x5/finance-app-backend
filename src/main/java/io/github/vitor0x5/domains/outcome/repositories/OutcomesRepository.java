package io.github.vitor0x5.domains.outcome.repositories;

import io.github.vitor0x5.domains.income.entities.Income;
import io.github.vitor0x5.domains.outcome.entities.Outcome;

import java.util.Optional;
import java.util.UUID;

public interface OutcomesRepository {
    Outcome save(Outcome outcome);
    Optional<Outcome> findByUserId(UUID userId);
}
