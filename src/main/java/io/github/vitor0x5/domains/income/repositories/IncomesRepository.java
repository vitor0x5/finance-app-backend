package io.github.vitor0x5.domains.income.repositories;

import io.github.vitor0x5.domains.income.entities.Income;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IncomesRepository {
    Income save(Income income);
    List<Income> findByUserId(UUID userId);
    Optional<Income> findById(UUID incomeId);
    void delete(Income income);
}
