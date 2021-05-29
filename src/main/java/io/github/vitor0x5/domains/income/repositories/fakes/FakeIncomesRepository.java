package io.github.vitor0x5.domains.income.repositories.fakes;

import io.github.vitor0x5.domains.income.entities.Income;
import io.github.vitor0x5.domains.income.repositories.IncomesRepository;

import java.util.*;

public class FakeIncomesRepository implements IncomesRepository {

    private List<Income> incomes = new ArrayList<>();

    @Override
    public Income save(Income income) {
        incomes.add(income);
        return income;
    }

    @Override
    public List<Income> findByUserId(UUID userId) {
        List<Income> incomesFiltered = new ArrayList<>();
        for(Income i: incomes) {
            if(i.getUser().getId().equals(userId))
                incomesFiltered.add(i);
        }
        return incomesFiltered;
    }

    @Override
    public Optional<Income> findById(UUID incomeId) {
        for(Income i: incomes) {
            if(i.getId().equals(incomeId))
                return Optional.of(i);
        }
        return Optional.empty();
    }

    @Override
    public void delete(Income income) {
        incomes.remove(income);
    }
}
