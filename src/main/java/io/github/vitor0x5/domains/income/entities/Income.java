package io.github.vitor0x5.domains.income.entities;

import io.github.vitor0x5.domains.user.entities.AppUser;
import io.github.vitor0x5.shared.BaseEntity;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "incomes")
public class Income extends BaseEntity {
    @Type(type = "pg-uuid")
    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private AppUser user;

    private String source;
    private String description;
    private Long value;

    @Column(name = "income_date")
    private LocalDate incomeDate;

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public LocalDate getIncomeDate() {
        return incomeDate;
    }

    public void setIncomeDate(LocalDate incomeDate) {
        this.incomeDate = incomeDate;
    }

}
