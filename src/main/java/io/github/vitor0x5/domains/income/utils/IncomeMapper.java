package io.github.vitor0x5.domains.income.utils;

import io.github.vitor0x5.domains.income.dtos.CreateIncomeDTO;
import io.github.vitor0x5.domains.income.entities.Income;
import io.github.vitor0x5.domains.user.entities.AppUser;
import io.github.vitor0x5.shared.encoder.Encoder;

public class IncomeMapper {

    public static Income createIncome(CreateIncomeDTO dto, AppUser user, Encoder encoder) {
        Income income = new Income();
        income.setUser(user);
        income.setPlace(encoder.encode(dto.place));
        income.setDescription(encoder.encode(dto.description));
        income.setValue(encoder.encode(dto.value.toString()));
        income.setIncomeDate(dto.incomeDate);

        return income;
    }
}
