package io.github.vitor0x5.domains.income.dtos;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateIncomeDTO {
    @NotNull(message = "Source can´t be null")
    public String source;

    @NotNull(message = "Description can´t be null")
    public String description;

    @NotNull(message = "Value can´t be null")
    public BigDecimal value;

    @NotNull(message = "Income date can´t be null")
    public LocalDate incomeDate;
}
