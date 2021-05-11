package io.github.vitor0x5.domains.outcome.repositories;

import io.github.vitor0x5.domains.outcome.entities.Outcome;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OutcomesRepository {
    Outcome save(Outcome outcome);
    List<Outcome> findByUserId(UUID userId);
    Optional<Outcome> findById(UUID outcomeId);
    void delete(Outcome outcome);
}
