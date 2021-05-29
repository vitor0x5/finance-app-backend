package io.github.vitor0x5.domains.user.services;

import io.github.vitor0x5.domains.user.dto.SignUpDTO;
import io.github.vitor0x5.domains.user.dto.UserResponseDataDTO;
import io.github.vitor0x5.domains.user.entities.AppUser;
import io.github.vitor0x5.domains.user.repositories.fakes.FakeUsersRepository;

import io.github.vitor0x5.domains.user.utils.mocks.UserMocksFactory;
import io.github.vitor0x5.shared.encoder.fakes.FakeEncoder;
import io.github.vitor0x5.shared.errors.types.BusinessException;
import io.github.vitor0x5.shared.mappers.MapperProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

class CreateUserServiceTest {

    private CreateUserService createUserService;
    private ModelMapper mapper;
    private SignUpDTO user1;

    @BeforeEach
    public void before() {
        FakeUsersRepository repository = new FakeUsersRepository();
        FakeEncoder encoder = new FakeEncoder();
        MapperProducer mapperProducer = new MapperProducer();
        mapper = mapperProducer.get();

        createUserService = new CreateUserService(repository, encoder, mapper);

        user1 = UserMocksFactory.mockUser1SignUpDTO();
        repository.save(mapper.map(user1, AppUser.class));
    }

    @Test
    void testCreateUserWithValidEmailAndPasword() {
        SignUpDTO newUser = UserMocksFactory.mockUser2SignUpDTO();
        UserResponseDataDTO createdUserData = createUserService.execute(newUser);
        UserResponseDataDTO newUserData = mapper.map(newUser, UserResponseDataDTO.class);
        assertThatUsersAreEqual(newUserData, createdUserData);
    }

    @Test
    void testCreateUserWithAlreadyUsedEmail() {
        SignUpDTO newUser = UserMocksFactory.mockUser2SignUpDTO();
        newUser.email = user1.email;
        try{
            createUserService.execute(newUser);
        } catch (RuntimeException ex) {
            assertThat(ex).isInstanceOf(BusinessException.class);
        }
    }

    @Test
    void testCreateUserWithInvalidPassword() {
        SignUpDTO newUser = UserMocksFactory.mockUser2SignUpDTO();
        newUser.password = "abc";
        try{
            createUserService.execute(newUser);
        } catch (RuntimeException ex) {
            assertThat(ex).isInstanceOf(RuntimeException.class);
        }
    }

    @Test
    void testCreateUserWithInvalidEmail() {
        SignUpDTO newUser = UserMocksFactory.mockUser2SignUpDTO();
        newUser.email = "user2email.com";
        try{
            createUserService.execute(newUser);
        } catch (RuntimeException ex) {
            assertThat(ex).isInstanceOf(BusinessException.class);
        }
    }

    private void assertThatUsersAreEqual(UserResponseDataDTO user1, UserResponseDataDTO user2) {
        assert(user1.name).equals(user2.name);
        assert(user1.email).equals(user2.email);
    }
}