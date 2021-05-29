package io.github.vitor0x5.domains.income.controllers;

import io.github.vitor0x5.domains.income.dtos.CreateIncomeDTO;
import io.github.vitor0x5.domains.income.dtos.IncomeResponseDataDTO;
import io.github.vitor0x5.domains.income.services.CreateIncomeService;
import io.github.vitor0x5.domains.income.services.DeleteIncomeService;
import io.github.vitor0x5.domains.income.services.GetAllIncomesFromAnUserService;
import io.github.vitor0x5.domains.income.services.UpdateIncomeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/incomes")
public class IncomesController {
    private final CreateIncomeService createIncomeService;
    private final DeleteIncomeService deleteIncomeService;
    private final UpdateIncomeService updateIncomeService;
    private final GetAllIncomesFromAnUserService getAllIncomesFromAnUserService;

    public IncomesController(CreateIncomeService createIncomeService,
                             DeleteIncomeService deleteIncomeService,
                             UpdateIncomeService updateIncomeService,
                             GetAllIncomesFromAnUserService getAllIncomesFromAnUserService
    ) {
        this.createIncomeService = createIncomeService;
        this.deleteIncomeService = deleteIncomeService;
        this.updateIncomeService = updateIncomeService;
        this.getAllIncomesFromAnUserService = getAllIncomesFromAnUserService;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public IncomeResponseDataDTO createIncome(
            @RequestAttribute("userEmail") String userEmail,
            @Valid @RequestBody CreateIncomeDTO incomeData
    ) {
        return createIncomeService.execute(incomeData, userEmail);
    }

    @DeleteMapping("/delete/{incomeId}")
    public void deleteIncome(
            @RequestAttribute("userEmail") String userEmail,
            @PathVariable UUID incomeId
    ) {
        deleteIncomeService.execute(incomeId, userEmail);
    }

    @PutMapping("/update/{incomeId}")
    public void updateIncome(
            @RequestAttribute("userEmail") String userEmail,
            @PathVariable UUID incomeId,
            @Valid @RequestBody CreateIncomeDTO incomeData
    ) {
        updateIncomeService.execute(incomeId, incomeData, userEmail);
    }

    @GetMapping("/all")
    public List<IncomeResponseDataDTO> getAllIncomesFromAnUser(@RequestAttribute("userEmail") String userEmail) {
        return getAllIncomesFromAnUserService.execute(userEmail);
    }

}
