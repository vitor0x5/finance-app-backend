package io.github.vitor0x5.domains.outcome.services;

import io.github.vitor0x5.domains.outcome.dtos.OutcomeResponseDataDTO;
import io.github.vitor0x5.domains.outcome.entities.Outcome;
import io.github.vitor0x5.domains.user.entities.AppUser;
import io.github.vitor0x5.domains.user.repositories.UsersRepository;
import io.github.vitor0x5.shared.errors.types.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class GetAllOutcomesFromAnUserService {
    private final UsersRepository usersRepository;
    private final ModelMapper mapper;

    public GetAllOutcomesFromAnUserService(UsersRepository usersRepository, ModelMapper mapper) {
        this.usersRepository = usersRepository;
        this.mapper = mapper;
    }

    @Transactional
    public List<OutcomeResponseDataDTO> execute(String email) {
        AppUser user = usersRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(NotFoundException.userNotFound));

        List<Outcome> outcomes =  user.getOutcomes();
        outcomes.sort(Comparator.comparing(Outcome::getOutcomeDate));

        return buildResponseData(outcomes);
    }

    private List<OutcomeResponseDataDTO> buildResponseData(List<Outcome> outcomes) {
        List<OutcomeResponseDataDTO> responseData = new ArrayList<>();

        outcomes.forEach((i) -> {
            responseData.add(mapper.map(i, OutcomeResponseDataDTO.class));
        });

        return responseData;
    }
}
