package io.github.vitor0x5.domains.income.utils;

import io.github.vitor0x5.domains.income.dtos.CreateIncomeDTO;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

public class IncomeMocksFactory {

    public static CreateIncomeDTO mockCreateIncomeDTO1() {
        CreateIncomeDTO income = new CreateIncomeDTO();

        income.description = "test description";
        income.source = "test source";
        income.value = BigDecimal.valueOf(20.00);
        income.incomeDate = LocalDate.now();

        return income;
    }
}
