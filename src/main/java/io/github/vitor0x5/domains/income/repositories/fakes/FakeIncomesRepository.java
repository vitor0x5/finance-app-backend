package io.github.vitor0x5.domains.income.repositories.fakes;

import io.github.vitor0x5.domains.income.entities.Income;
import io.github.vitor0x5.domains.income.repositories.IncomesRepository;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FakeIncomesRepository implements IncomesRepository {

    private List<Income> incomes = new ArrayList<>();

    @Override
    public Income save(Income income) {
        incomes.add(income);
        return income;
    }

    @Override
    public Optional<Income> findByUserId(UUID userId) {
        for(Income i: incomes) {
            if(i.getId().equals(userId))
                return Optional.of(i);
        }
        return Optional.empty();
    }
}
