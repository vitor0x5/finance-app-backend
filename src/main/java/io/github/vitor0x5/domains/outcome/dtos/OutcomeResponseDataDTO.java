package io.github.vitor0x5.domains.outcome.dtos;

import java.time.LocalDate;
import java.util.UUID;

public class OutcomeResponseDataDTO {
    public UUID id;
    public String place;
    public String description;
    public Double value;
    public LocalDate outcomeDate;
}
