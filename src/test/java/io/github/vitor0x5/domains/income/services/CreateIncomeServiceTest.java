package io.github.vitor0x5.domains.income.services;

import io.github.vitor0x5.domains.income.dtos.CreateIncomeDTO;
import io.github.vitor0x5.domains.income.dtos.IncomeResponseDataDTO;
import io.github.vitor0x5.domains.income.repositories.IncomesRepository;
import io.github.vitor0x5.domains.income.repositories.fakes.FakeIncomesRepository;
import io.github.vitor0x5.domains.income.utils.IncomeMocksFactory;
import io.github.vitor0x5.domains.user.dto.SignUpDTO;
import io.github.vitor0x5.domains.user.repositories.UsersRepository;
import io.github.vitor0x5.domains.user.repositories.fakes.FakeUsersRepository;
import io.github.vitor0x5.domains.user.utils.mocks.UserMocksFactory;
import io.github.vitor0x5.shared.encoder.Encoder;
import io.github.vitor0x5.shared.encoder.fakes.FakeEncoder;
import static org.assertj.core.api.Assertions.assertThat;

import io.github.vitor0x5.shared.errors.types.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

public class CreateIncomeServiceTest {
    private CreateIncomeService createIncomeService;
    private IncomesRepository incomesRepository;
    private UsersRepository usersRepository;

    private SignUpDTO userData;

    @BeforeEach
    private void beforeEach() {
        incomesRepository = new FakeIncomesRepository();
        usersRepository = new FakeUsersRepository();
        createIncomeService = new CreateIncomeService(incomesRepository, usersRepository);

        userData = UserMocksFactory.mockUser1SignUpDTO();
        usersRepository.save(SignUpDTO.toAppUser(userData));
    }

    @Test
    public void createIncomeWithValidUserEmail() {
        CreateIncomeDTO income = IncomeMocksFactory.mockCreateIncomeDTO1();
        IncomeResponseDataDTO createdIncome = createIncomeService.execute(income, userData.getEmail());

        IncomeResponseDataDTO expectedResponse = new IncomeResponseDataDTO(
                createdIncome.id,
                income.source,
                income.description,
                income.value,
                income.incomeDate
        );

        assertThatIncomesAreEquals(createdIncome, expectedResponse);
    }

    @Test
    public void createIncomeWithIvalidUserEmail() {
        CreateIncomeDTO income = IncomeMocksFactory.mockCreateIncomeDTO1();

        try{
            createIncomeService.execute(income, "invalid@email.com");
        } catch (RuntimeException e) {
            assertThat(e).isInstanceOf(NotFoundException.class);
        }
    }

    private void assertThatIncomesAreEquals(IncomeResponseDataDTO i1, IncomeResponseDataDTO i2){
        assert(i1.source.equals(i2.source));
        assert(i1.incomeDate.equals(i2.incomeDate));
        assert(i1.description.equals(i2.description));
        assert(i1.value.equals(i2.value));
    }
}
