package io.github.vitor0x5.domains.income.services;

import io.github.vitor0x5.domains.income.dtos.IncomeResponseDataDTO;
import io.github.vitor0x5.domains.income.entities.Income;
import io.github.vitor0x5.domains.user.entities.AppUser;
import io.github.vitor0x5.domains.user.repositories.UsersRepository;
import io.github.vitor0x5.shared.errors.types.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
public class GetAllIncomesFromAnUserService {
    private final UsersRepository usersRepository;
    private final ModelMapper mapper;

    public GetAllIncomesFromAnUserService(UsersRepository usersRepository, ModelMapper mapper) {
        this.usersRepository = usersRepository;
        this.mapper = mapper;
    }

    @Transactional
    public List<IncomeResponseDataDTO> execute(String email) {
        AppUser user = usersRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(NotFoundException.userNotFound));

        List<Income> incomes =  user.getIncomes();
        incomes.sort(Comparator.comparing(Income::getIncomeDate));

        return buildResponseData(incomes);
    }

    private List<IncomeResponseDataDTO> buildResponseData(List<Income> incomes) {
        List<IncomeResponseDataDTO> responseData = new ArrayList<>();

        incomes.forEach((i) -> {
            responseData.add(mapper.map(i, IncomeResponseDataDTO.class));
        });

        return responseData;
    }
}
