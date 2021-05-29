package io.github.vitor0x5.domains.income.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class IncomeResponseDataDTO {
    public UUID id;
    public String source;
    public String description;
    public BigDecimal value;
    public LocalDate incomeDate;
}
