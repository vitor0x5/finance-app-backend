package io.github.vitor0x5.domains.outcome.dtos;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

public class CreateOutcomeDTO {
    @NotNull(message = "Place can´t be null")
    public String place;

    @NotNull(message = "Description can´t be null")
    public String description;

    @NotNull(message = "Value can´t be null")
    public BigDecimal value;

    @NotNull(message = "Outcome date can´t be null")
    public LocalDate outcomeDate;
}
