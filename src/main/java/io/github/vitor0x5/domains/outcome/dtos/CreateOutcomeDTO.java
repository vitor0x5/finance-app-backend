package io.github.vitor0x5.domains.outcome.dtos;

import java.sql.Date;
import java.util.UUID;

public class CreateOutcomeDTO {
    public UUID userId;
    public String description;
    public Double value;
    public Date outcomeDate;
}
