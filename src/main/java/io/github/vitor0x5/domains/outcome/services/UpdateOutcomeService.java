package io.github.vitor0x5.domains.outcome.services;

import io.github.vitor0x5.domains.outcome.dtos.CreateOutcomeDTO;
import io.github.vitor0x5.domains.outcome.dtos.OutcomeResponseDataDTO;
import io.github.vitor0x5.domains.outcome.entities.Outcome;
import io.github.vitor0x5.domains.outcome.repositories.OutcomesRepository;
import io.github.vitor0x5.shared.errors.types.BusinessException;
import io.github.vitor0x5.shared.errors.types.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UpdateOutcomeService {

    private final OutcomesRepository outcomesRepository;
    private final ModelMapper mapper;

    public UpdateOutcomeService(OutcomesRepository outcomesRepository, ModelMapper mapper) {
        this.outcomesRepository = outcomesRepository;
        this.mapper = mapper;
    }

    @Transactional
    public OutcomeResponseDataDTO execute(UUID outcomeId, CreateOutcomeDTO outcomeData, String userEmail) {
        Outcome outcome = outcomesRepository.findById(outcomeId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.outcomeNotFound));

        String outcomeUserEmail = outcome.getUser().getEmail();

        if (!outcomeUserEmail.equals(userEmail)){
            throw new BusinessException(BusinessException.incorrectUser);
        }

        Outcome updatedOutcome = mapper.map(outcomeData, Outcome.class);
        updatedOutcome.setUser(outcome.getUser());
        updatedOutcome.setId(outcome.getId());

        outcomesRepository.save(updatedOutcome);
        return mapper.map(updatedOutcome, OutcomeResponseDataDTO.class);
    }
}
