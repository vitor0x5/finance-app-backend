package io.github.vitor0x5.domains.outcome.entities;

import io.github.vitor0x5.domains.user.entities.AppUser;
import io.github.vitor0x5.shared.BaseEntity;
import io.github.vitor0x5.shared.converters.monetary.MonetaryTypeAttributeConverter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "outcomes")
public class Outcome extends BaseEntity {
    @Type(type = "pg-uuid")
    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private AppUser user;

    private String description;
    private String place;

    @Convert(converter = MonetaryTypeAttributeConverter.class)
    private BigDecimal value;

    @Column(name = "outcome_date")
    private LocalDate outcomeDate;

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public LocalDate getOutcomeDate() {
        return outcomeDate;
    }

    public void setOutcomeDate(LocalDate outcomeDate) {
        this.outcomeDate = outcomeDate;
    }
}
