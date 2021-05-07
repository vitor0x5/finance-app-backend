package io.github.vitor0x5.domains.income.services;

import io.github.vitor0x5.domains.income.dtos.CreateIncomeDTO;
import io.github.vitor0x5.domains.income.dtos.IncomeResponseDataDTO;
import io.github.vitor0x5.domains.income.entities.Income;
import io.github.vitor0x5.domains.income.repositories.IncomesRepository;
import io.github.vitor0x5.shared.errors.types.BusinessException;
import io.github.vitor0x5.shared.errors.types.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UpdateIncomeService {

    private final IncomesRepository incomesRepository;

    public UpdateIncomeService(IncomesRepository incomesRepository) {
        this.incomesRepository = incomesRepository;
    }

    @Transactional
    public IncomeResponseDataDTO execute(UUID incomeId, CreateIncomeDTO incomeData, String userEmail) {
        Income income = incomesRepository.findById(incomeId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.incomeNotFound));

        if (!income.getUser().getEmail().equals(userEmail)){
            throw new BusinessException(BusinessException.incorrectUser);
        }

        Long formattedValue = incomeData.value.movePointRight(2).longValue();

        income.setSource(incomeData.source);
        income.setDescription(incomeData.description);
        income.setValue(formattedValue);
        income.setIncomeDate(incomeData.incomeDate);

        incomesRepository.save(income);
        return new IncomeResponseDataDTO(
                income.getId(),
                income.getSource(),
                income.getDescription(),
                incomeData.value,
                income.getIncomeDate());
    }
}
