package io.github.vitor0x5.domains.income.utils;

import io.github.vitor0x5.domains.income.dtos.CreateIncomeDTO;
import io.github.vitor0x5.domains.income.dtos.IncomeResponseDataDTO;
import io.github.vitor0x5.domains.income.entities.Income;
import io.github.vitor0x5.domains.user.entities.AppUser;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class IncomeMapper {

    public static Income createIncome(CreateIncomeDTO dto, AppUser user) {
        Income income = new Income();

        Long longValue = dto.value.movePointRight(2).longValue();

        income.setUser(user);
        income.setSource(dto.source);
        income.setDescription(dto.description);
        income.setValue(longValue);
        income.setIncomeDate(dto.incomeDate);

        return income;
    }

    public static List<IncomeResponseDataDTO> createIncomeResponseDTOList(List<Income> incomes) {
        List<IncomeResponseDataDTO> response = new ArrayList<>();

        incomes.forEach((i) -> {
            response.add(new IncomeResponseDataDTO(
                    i.getId(),
                    i.getSource(),
                    i.getDescription(),
                    BigDecimal.valueOf(i.getValue()).movePointLeft(2),
                    i.getIncomeDate()
            ));
        });

        return response;
    }
}
