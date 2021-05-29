package io.github.vitor0x5.domains.income.repositories.implementations;

import io.github.vitor0x5.domains.income.entities.Income;
import io.github.vitor0x5.domains.income.repositories.IncomesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IncomesJpaRepository extends IncomesRepository, JpaRepository<Income, UUID> {

    List<Income> findByUserId(UUID userid);
}
