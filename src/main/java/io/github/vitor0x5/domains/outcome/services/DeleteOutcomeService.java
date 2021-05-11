package io.github.vitor0x5.domains.outcome.services;

import io.github.vitor0x5.domains.outcome.entities.Outcome;
import io.github.vitor0x5.domains.outcome.repositories.OutcomesRepository;
import io.github.vitor0x5.shared.errors.types.BusinessException;
import io.github.vitor0x5.shared.errors.types.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class DeleteOutcomeService {

    private final OutcomesRepository outcomesRepository;

    public DeleteOutcomeService(OutcomesRepository outcomesRepository) {
        this.outcomesRepository = outcomesRepository;
    }

    @Transactional
    public void execute(UUID outcomeId, String userEmail) {
        Outcome outcome = outcomesRepository.findById(outcomeId)
                .orElseThrow(() ->
                    new NotFoundException(NotFoundException.outcomeNotFound));

        if(outcome.getUser().getEmail().equals(userEmail)) {
            outcomesRepository.delete(outcome);
            return;
        }

        throw new BusinessException(BusinessException.incorrectUser);
    }
}
