package io.github.vitor0x5.domains.income.services;

import io.github.vitor0x5.domains.income.dtos.IncomeResponseDataDTO;
import io.github.vitor0x5.domains.income.entities.Income;
import io.github.vitor0x5.domains.income.repositories.IncomesRepository;
import io.github.vitor0x5.domains.income.utils.IncomeMapper;
import io.github.vitor0x5.domains.user.entities.AppUser;
import io.github.vitor0x5.domains.user.repositories.UsersRepository;
import io.github.vitor0x5.shared.errors.types.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
public class GetAllIncomesFromAnUserService {
    private final IncomesRepository incomesRepository;
    private final UsersRepository usersRepository;

    public GetAllIncomesFromAnUserService(IncomesRepository incomesRepository, UsersRepository usersRepository) {
        this.incomesRepository = incomesRepository;
        this.usersRepository = usersRepository;
    }

    @Transactional
    public List<IncomeResponseDataDTO> execute(String email) {
        AppUser user = usersRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(NotFoundException.userNotFound));

        List<Income> incomes =  incomesRepository.findByUserId(user.getId());
        incomes.sort(Comparator.comparing(Income::getIncomeDate));

        return IncomeMapper.createIncomeResponseDTOList(incomes);
    }
}
