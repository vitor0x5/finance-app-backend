package io.github.vitor0x5.domains.outcome.entities;

import io.github.vitor0x5.domains.user.entities.AppUser;
import io.github.vitor0x5.shared.BaseEntity;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "outcomes")
public class Outcome extends BaseEntity {
    @Type(type = "pg-uuid")
    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private AppUser user;

    private String description;
    private String value;

    @Column(name = "outcome_date")
    private Date outcomeDate;

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getOutcomeDate() {
        return outcomeDate;
    }

    public void setOutcomeDate(Date outcomeDate) {
        this.outcomeDate = outcomeDate;
    }
}
