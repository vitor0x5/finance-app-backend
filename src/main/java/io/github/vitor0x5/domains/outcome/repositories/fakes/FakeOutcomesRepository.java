package io.github.vitor0x5.domains.outcome.repositories.fakes;

import io.github.vitor0x5.domains.outcome.entities.Outcome;
import io.github.vitor0x5.domains.outcome.repositories.OutcomesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FakeOutcomesRepository implements OutcomesRepository {
    private List<Outcome> outcomes = new ArrayList<>();

    @Override
    public Outcome save(Outcome outcomes) {
        this.outcomes.add(outcomes);
        return outcomes;
    }

    @Override
    public List<Outcome> findByUserId(UUID userId) {
        List<Outcome> outcomesFiltered = new ArrayList<>();
        for(Outcome i: outcomes) {
            if(i.getUser().getId().equals(userId))
                outcomesFiltered.add(i);
        }
        return outcomesFiltered;
    }

    @Override
    public Optional<Outcome> findById(UUID outcomeId) {
        for(Outcome i: outcomes) {
            if(i.getId().equals(outcomeId))
                return Optional.of(i);
        }
        return Optional.empty();
    }

    @Override
    public void delete(Outcome outcome) {
        outcomes.remove(outcome);
    }
}
