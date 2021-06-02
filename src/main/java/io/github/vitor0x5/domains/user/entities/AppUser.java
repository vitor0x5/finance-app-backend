package io.github.vitor0x5.domains.user.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.vitor0x5.domains.income.entities.Income;
import io.github.vitor0x5.domains.outcome.entities.Outcome;
import io.github.vitor0x5.shared.BaseEntity;
import io.github.vitor0x5.shared.encoder.Encoder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
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
}

