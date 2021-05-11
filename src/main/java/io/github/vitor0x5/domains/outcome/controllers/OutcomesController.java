package io.github.vitor0x5.domains.outcome.controllers;

import io.github.vitor0x5.domains.outcome.dtos.CreateOutcomeDTO;
import io.github.vitor0x5.domains.outcome.dtos.OutcomeResponseDataDTO;
import io.github.vitor0x5.domains.outcome.services.CreateOutcomeService;
import io.github.vitor0x5.domains.outcome.services.DeleteOutcomeService;
import io.github.vitor0x5.domains.outcome.services.GetAllOutcomesFromAnUserService;
import io.github.vitor0x5.domains.outcome.services.UpdateOutcomeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/outcomes")
public class OutcomesController {
    private final CreateOutcomeService createOutcomeService;
    private final DeleteOutcomeService deleteOutcomeService;
    private final UpdateOutcomeService updateOutcomeService;
    private final GetAllOutcomesFromAnUserService getAllOutcomesFromAnUserService;

    public OutcomesController(CreateOutcomeService createOutcomeService,
                              DeleteOutcomeService deleteOutcomeService,
                              UpdateOutcomeService updateOutcomeService,
                              GetAllOutcomesFromAnUserService getAllOutcomesFromAnUserService
    ) {
        this.createOutcomeService = createOutcomeService;
        this.deleteOutcomeService = deleteOutcomeService;
        this.updateOutcomeService = updateOutcomeService;
        this.getAllOutcomesFromAnUserService = getAllOutcomesFromAnUserService;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public OutcomeResponseDataDTO createOutcome(
            @RequestAttribute("userEmail") String userEmail,
            @Valid @RequestBody CreateOutcomeDTO outcomeData
    ) {
        return createOutcomeService.execute(outcomeData, userEmail);
    }

    @DeleteMapping("/delete/{outcomeId}")
    public void deleteOutcome(
            @RequestAttribute("userEmail") String userEmail,
            @PathVariable UUID outcomeId
    ) {
        deleteOutcomeService.execute(outcomeId, userEmail);
    }

    @PutMapping("/update/{outcomeId}")
    public void updateOutcome(
            @RequestAttribute("userEmail") String userEmail,
            @PathVariable UUID outcomeId,
            @Valid @RequestBody CreateOutcomeDTO outcomeData
    ) {
        updateOutcomeService.execute(outcomeId, outcomeData, userEmail);
    }

    @GetMapping("/all")
    public List<OutcomeResponseDataDTO> getAllOutcomesFromAnUser(@RequestAttribute("userEmail") String userEmail) {
        return getAllOutcomesFromAnUserService.execute(userEmail);
    }

}
