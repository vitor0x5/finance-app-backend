package io.github.vitor0x5.domains.income.services;

import io.github.vitor0x5.domains.income.dtos.CreateIncomeDTO;
import io.github.vitor0x5.domains.income.dtos.IncomeResponseDataDTO;
import io.github.vitor0x5.domains.income.entities.Income;
import io.github.vitor0x5.domains.income.repositories.IncomesRepository;
import io.github.vitor0x5.domains.income.utils.IncomeMapper;
import io.github.vitor0x5.domains.user.entities.AppUser;
import io.github.vitor0x5.domains.user.repositories.UsersRepository;
import io.github.vitor0x5.shared.encoder.Encoder;
import io.github.vitor0x5.shared.errors.types.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CreateIncomeService {
    private final IncomesRepository incomesRepository;
    private final UsersRepository usersRepository;
    private final Encoder encoder;

    public CreateIncomeService(IncomesRepository incomesRepository, UsersRepository usersRepository, Encoder encoder) {
        this.incomesRepository = incomesRepository;
        this.usersRepository = usersRepository;
        this.encoder = encoder;
    }

    @Transactional
    public IncomeResponseDataDTO execute(CreateIncomeDTO incomeData, String userEmail) {
        AppUser user = usersRepository.findByEmail(userEmail)
                .orElseThrow(() -> {
                    throw new NotFoundException(NotFoundException.userNotFound);
                });

        Income income = IncomeMapper.createIncome(incomeData, user, encoder);
        incomesRepository.save(income);

        return new IncomeResponseDataDTO(
                incomeData.place,
                incomeData.description,
                incomeData.value,
                incomeData.incomeDate
        );
    }
}
