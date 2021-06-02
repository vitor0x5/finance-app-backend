package io.github.vitor0x5.domains.transaction.services;

import io.github.vitor0x5.domains.transaction.dtos.CreateTransactionDTO;
import io.github.vitor0x5.domains.transaction.dtos.TransactionResponseDataDTO;
import io.github.vitor0x5.domains.transaction.entities.Transaction;
import io.github.vitor0x5.domains.transaction.repositories.TransactionsRepository;
import io.github.vitor0x5.domains.user.entities.AppUser;
import io.github.vitor0x5.domains.user.repositories.UsersRepository;
import io.github.vitor0x5.shared.errors.types.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CreateTransactionService {
    private final TransactionsRepository transactionsRepository;
    private final UsersRepository usersRepository;
    private final ModelMapper mapper;

    public CreateTransactionService(
            TransactionsRepository transactionsRepository,
            UsersRepository usersRepository,
            ModelMapper mapper
    ) {
        this.transactionsRepository = transactionsRepository;
        this.usersRepository = usersRepository;
        this.mapper = mapper;
    }

    @Transactional
    public TransactionResponseDataDTO execute(CreateTransactionDTO transactionData, String userEmail) {
        AppUser user = usersRepository.findByEmail(userEmail)
                .orElseThrow(() -> {
                    throw new NotFoundException(NotFoundException.userNotFound);
                });

        Transaction transaction = mapper.map(transactionData, Transaction.class);
        transaction.setUser(user);
        transactionsRepository.save(transaction);

        return mapper.map(transaction, TransactionResponseDataDTO.class);
    }
}
