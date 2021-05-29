package io.github.vitor0x5.domains.outcome.services;

import io.github.vitor0x5.domains.outcome.dtos.CreateOutcomeDTO;
import io.github.vitor0x5.domains.outcome.dtos.OutcomeResponseDataDTO;
import io.github.vitor0x5.domains.outcome.entities.Outcome;
import io.github.vitor0x5.domains.outcome.repositories.OutcomesRepository;
import io.github.vitor0x5.domains.user.entities.AppUser;
import io.github.vitor0x5.domains.user.repositories.UsersRepository;
import io.github.vitor0x5.shared.errors.types.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CreateOutcomeService {
    private final OutcomesRepository outcomesRepository;
    private final UsersRepository usersRepository;
    private final ModelMapper mapper;

    public CreateOutcomeService(
            OutcomesRepository outcomesRepository,
            UsersRepository usersRepository,
            ModelMapper mapper
    ) {
        this.outcomesRepository = outcomesRepository;
        this.usersRepository = usersRepository;
        this.mapper = mapper;
    }

    @Transactional
    public OutcomeResponseDataDTO execute(CreateOutcomeDTO outcomeDTO, String userEmail) {
        AppUser user = usersRepository.findByEmail(userEmail)
                .orElseThrow(() -> {
                    throw new NotFoundException(NotFoundException.userNotFound);
                });

        Outcome outcome = mapper.map(outcomeDTO, Outcome.class);
        outcome.setUser(user);
        outcomesRepository.save(outcome);

        return mapper.map(outcome, OutcomeResponseDataDTO.class);
    }
}
