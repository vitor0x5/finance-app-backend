package io.github.vitor0x5.domains.outcome.entities;

import io.github.vitor0x5.domains.user.entities.AppUser;
import io.github.vitor0x5.shared.BaseEntity;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Date;

@Entity
@Table(name = "outcomes")
public class Outcome extends BaseEntity {
    @Type(type = "pg-uuid")
    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private AppUser user;

    private String description;
    private Long value;

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

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
