package io.github.vitor0x5.domains.income.services;

import io.github.vitor0x5.domains.income.dtos.CreateIncomeDTO;
import io.github.vitor0x5.domains.income.dtos.IncomeResponseDataDTO;
import io.github.vitor0x5.domains.income.entities.Income;
import io.github.vitor0x5.domains.income.repositories.IncomesRepository;
import io.github.vitor0x5.shared.errors.types.BusinessException;
import io.github.vitor0x5.shared.errors.types.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UpdateIncomeService {

    private final IncomesRepository incomesRepository;
    private final ModelMapper mapper;

    public UpdateIncomeService(IncomesRepository incomesRepository, ModelMapper mapper) {
        this.incomesRepository = incomesRepository;
        this.mapper = mapper;
    }

    @Transactional
    public IncomeResponseDataDTO execute(UUID incomeId, CreateIncomeDTO incomeData, String userEmail) {
        Income income = incomesRepository.findById(incomeId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.incomeNotFound));

        String incomeUserEmail = income.getUser().getEmail();

        if (!incomeUserEmail.equals(userEmail)){
            throw new BusinessException(BusinessException.incorrectUser);
        }

        Income updatedIncome = mapper.map(incomeData, Income.class);
        updatedIncome.setUser(income.getUser());
        updatedIncome.setId(income.getId());

        incomesRepository.save(updatedIncome);
        return mapper.map(updatedIncome, IncomeResponseDataDTO.class);
    }
}
