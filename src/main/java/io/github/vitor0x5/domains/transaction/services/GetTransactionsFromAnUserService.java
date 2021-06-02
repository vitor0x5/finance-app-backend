package io.github.vitor0x5.domains.transaction.services;

import io.github.vitor0x5.domains.transaction.dtos.TransactionResponseDataDTO;
import io.github.vitor0x5.domains.transaction.entities.Transaction;
import io.github.vitor0x5.domains.transaction.repositories.TransactionsRepository;
import io.github.vitor0x5.domains.user.entities.AppUser;
import io.github.vitor0x5.domains.user.repositories.UsersRepository;
import io.github.vitor0x5.shared.errors.types.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

public class GetTransactionsFromAnUserService {
    private final UsersRepository usersRepository;
    private final TransactionsRepository transactionsRepository;
    private final ModelMapper mapper;

    public GetTransactionsFromAnUserService(UsersRepository usersRepository, TransactionsRepository transactionsRepository, ModelMapper mapper) {
        this.usersRepository = usersRepository;
        this.transactionsRepository = transactionsRepository;
        this.mapper = mapper;
    }

    @Transactional
    public List<TransactionResponseDataDTO> execute(String userEmail) {
        AppUser user = usersRepository.findByEmail(userEmail)
                .orElseThrow(() -> new NotFoundException(NotFoundException.userNotFound));

        return buildResponseData(transactionsRepository.findByUserId(user.getId()));
    }

    private List<TransactionResponseDataDTO> buildResponseData(List<Transaction> transactions) {
        return transactions.stream()
                .map(t -> mapper.map(t, TransactionResponseDataDTO.class))
                .collect(Collectors.toList());
    }
}
