package io.github.vitor0x5.domains.income.services;

import io.github.vitor0x5.domains.income.dtos.CreateIncomeDTO;
import io.github.vitor0x5.domains.income.dtos.IncomeResponseDataDTO;
import io.github.vitor0x5.domains.income.entities.Income;
import io.github.vitor0x5.domains.income.repositories.IncomesRepository;
import io.github.vitor0x5.domains.user.entities.AppUser;
import io.github.vitor0x5.domains.user.repositories.UsersRepository;
import io.github.vitor0x5.shared.errors.types.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CreateIncomeService {
    private final IncomesRepository incomesRepository;
    private final UsersRepository usersRepository;
    private final ModelMapper mapper;

    public CreateIncomeService(
            IncomesRepository incomesRepository,
            UsersRepository usersRepository,
            ModelMapper mapper
    ) {
        this.incomesRepository = incomesRepository;
        this.usersRepository = usersRepository;
        this.mapper = mapper;
    }

    @Transactional
    public IncomeResponseDataDTO execute(CreateIncomeDTO incomeData, String userEmail) {
        AppUser user = usersRepository.findByEmail(userEmail)
                .orElseThrow(() -> {
                    throw new NotFoundException(NotFoundException.userNotFound);
                });

        Income income = mapper.map(incomeData, Income.class);
        income.setUser(user);
        incomesRepository.save(income);

        return mapper.map(income, IncomeResponseDataDTO.class);
    }
}
