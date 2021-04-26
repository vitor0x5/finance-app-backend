package io.github.vitor0x5.domains.user.services;

import io.github.vitor0x5.domains.user.dto.SignUpDTO;
import io.github.vitor0x5.domains.user.dto.UserDataDTO;
import io.github.vitor0x5.domains.user.repositories.fakes.FakeUsersRepository;

import io.github.vitor0x5.shared.encoder.fakes.FakeEncoder;
import io.github.vitor0x5.shared.errors.types.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

class CreateUserServiceTest {

    private CreateUserService createUserService;

    private FakeEncoder encoder;

    private SignUpDTO user1;

    @BeforeEach
    public void before() {
        FakeUsersRepository repository = new FakeUsersRepository();
        FakeEncoder encoder = new FakeEncoder();
        createUserService = new CreateUserService(repository, encoder);

        user1 = createUser("user1@email.com", "1234@bcd", "User One");
        repository.save(SignUpDTO.toAppUser(user1));
    }

    private SignUpDTO createUser(String email, String password, String name) {
        SignUpDTO user = new SignUpDTO();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        return user;
    }

    @Test
    void testCreateUserWithValidEmailAndPasword() {
        SignUpDTO newUser = createUser("user2@email.com", "abcd@1234", "User Two");
        UserDataDTO createdUserData = createUserService.execute(newUser);
        UserDataDTO newUserData = new UserDataDTO(newUser.getName(), newUser.getEmail());
        assertThatUsersAreEqual(newUserData, createdUserData);
    }

    @Test
    void testCreateUserWithAlreadyUsedEmail() {
        SignUpDTO newUser = createUser(user1.getEmail(), "abcd@1234", "User Two");
        try{
            createUserService.execute(newUser);
        } catch (RuntimeException ex) {
            assertThat(ex).isInstanceOf(BusinessException.class);
        }
    }

    @Test
    void testCreateUserWithInvalidPassword() {
        SignUpDTO newUser = createUser("user2@email.com", "abc", "User Two");
        try{
            createUserService.execute(newUser);
        } catch (RuntimeException ex) {
            assertThat(ex).isInstanceOf(RuntimeException.class);
        }
    }

    @Test
    void testCreateUserWithInvalidEmail() {
        SignUpDTO newUser = createUser("user2email.com", "abcd@1234", "User Two");
        try{
            createUserService.execute(newUser);
        } catch (RuntimeException ex) {
            assertThat(ex).isInstanceOf(BusinessException.class);
        }
    }

    private void assertThatUsersAreEqual(UserDataDTO user1, UserDataDTO user2) {
        assert(user1.getName()).equals(user2.getName());
        assert(user1.getEmail()).equals(user2.getEmail());
    }
}