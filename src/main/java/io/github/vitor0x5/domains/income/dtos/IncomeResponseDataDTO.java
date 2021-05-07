package io.github.vitor0x5.domains.income.dtos;

import java.sql.Date;

public class IncomeResponseDataDTO {
    public String place;
    public String description;
    public Double value;
    public Date incomeDate;

    public IncomeResponseDataDTO(String place, String description, Double value, Date incomeDate) {
        this.place = place;
        this.description = description;
        this.value = value;
        this.incomeDate = incomeDate;
    }
}
