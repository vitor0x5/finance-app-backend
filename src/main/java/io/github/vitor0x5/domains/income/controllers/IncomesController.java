package io.github.vitor0x5.domains.income.controllers;

import io.github.vitor0x5.domains.income.dtos.CreateIncomeDTO;
import io.github.vitor0x5.domains.income.dtos.IncomeResponseDataDTO;
import io.github.vitor0x5.domains.income.services.CreateIncomeService;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/incomes")
public class IncomesController {
    private final CreateIncomeService createIncomeService;

    public IncomesController(CreateIncomeService createIncomeService) {
        this.createIncomeService = createIncomeService;
    }

    @PostMapping
    @RequestMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public IncomeResponseDataDTO createIncome(
            @RequestAttribute("userEmail") String userEmail,
            @RequestBody CreateIncomeDTO incomeData,
            CsrfToken token
    ) {
        return createIncomeService.execute(incomeData, userEmail);
    }
}
