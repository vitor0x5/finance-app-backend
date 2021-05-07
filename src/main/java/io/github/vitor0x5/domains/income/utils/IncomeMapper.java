package io.github.vitor0x5.domains.income.utils;

import io.github.vitor0x5.domains.income.dtos.CreateIncomeDTO;
import io.github.vitor0x5.domains.income.entities.Income;
import io.github.vitor0x5.domains.user.entities.AppUser;

import java.math.BigDecimal;
import java.sql.Date;

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
}
