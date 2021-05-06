package io.github.vitor0x5.domains.income.utils;

import io.github.vitor0x5.domains.income.dtos.CreateIncomeDTO;

import java.sql.Date;

public class IncomeMocksFactory {

    public static CreateIncomeDTO mockCreateIncomeDTO1() {
        CreateIncomeDTO income = new CreateIncomeDTO();

        income.description = "test description";
        income.place = "test place";
        income.value = 20.00;
        income.incomeDate = new Date(2021, 1, 1);

        return income;
    }
}
