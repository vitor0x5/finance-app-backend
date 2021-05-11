package io.github.vitor0x5.domains.user.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.vitor0x5.domains.income.entities.Income;
import io.github.vitor0x5.domains.outcome.entities.Outcome;
import io.github.vitor0x5.shared.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class AppUser extends BaseEntity {
    private String name;
    private String email;

    @JsonIgnore
    private String password;

    @OneToMany(targetEntity = Income.class, mappedBy = "user")
    private List<Income> incomes = new ArrayList<>();

    @OneToMany(targetEntity = Outcome.class, mappedBy = "user")
    private List<Outcome> outcomes = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Income> getIncomes() {
        return incomes;
    }

    public void setIncomes(List<Income> incomes) {
        this.incomes = incomes;
    }

    public List<Outcome> getOutcomes() {
        return outcomes;
    }

    public void setOutcomes(List<Outcome> outcomes) {
        this.outcomes = outcomes;
    }
}

