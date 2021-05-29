package io.github.vitor0x5.domains.income.services;

import io.github.vitor0x5.domains.income.entities.Income;
import io.github.vitor0x5.domains.income.repositories.IncomesRepository;
import io.github.vitor0x5.shared.errors.types.BusinessException;
import io.github.vitor0x5.shared.errors.types.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class DeleteIncomeService {

    private final IncomesRepository incomesRepository;

    public DeleteIncomeService(IncomesRepository incomesRepository) {
        this.incomesRepository = incomesRepository;
    }

    @Transactional
    public void execute(UUID incomeId, String userEmail) {
        Income income = incomesRepository.findById(incomeId)
                .orElseThrow(() ->
                    new NotFoundException(NotFoundException.incomeNotFound));

        if(income.getUser().getEmail().equals(userEmail)) {
            incomesRepository.delete(income);
            return;
        }

        throw new BusinessException(BusinessException.incorrectUser);
    }
}
