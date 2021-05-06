package io.github.vitor0x5.domains.user.services;

import io.github.vitor0x5.domains.user.dto.SignUpDTO;
import io.github.vitor0x5.domains.user.dto.UserDataDTO;
import io.github.vitor0x5.domains.user.repositories.fakes.FakeUsersRepository;
import io.github.vitor0x5.shared.encoder.fakes.FakeEncoder;
import io.github.vitor0x5.shared.errors.ApiErrors;
import io.github.vitor0x5.shared.errors.types.BusinessException;
import io.github.vitor0x5.shared.errors.types.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ShowProfileServiceTest {

    private ShowProfileService showProfileService;

    private FakeEncoder encoder;

    private SignUpDTO user1;

    @BeforeEach
    public void before() {
        FakeUsersRepository repository = new FakeUsersRepository();
        FakeEncoder encoder = new FakeEncoder();
        showProfileService = new ShowProfileService(repository);

        user1 = createUser("user1@email.com", "1234@bcd", "User One");
        repository.save(SignUpDTO.toAppUser(user1));
    }

    @Test
    void testShowProfileWithValidEmail() {
        UserDataDTO createdUserData = showProfileService.execute(user1.getEmail());
        UserDataDTO newUserData = new UserDataDTO(user1.getName(), user1.getEmail());
        assertThatUsersAreEqual(newUserData, createdUserData);
    }

    @Test
    void testShowProfileWithInvalidEmail() {
        try {
            showProfileService.execute("user2@email.com");
        } catch (RuntimeException e) {
            assertThat(e).isInstanceOf(NotFoundException.class);
        }


    }

    private SignUpDTO createUser(String email, String password, String name) {
        SignUpDTO user = new SignUpDTO();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        return user;
    }

    private void assertThatUsersAreEqual(UserDataDTO user1, UserDataDTO user2) {
        assert(user1.getName()).equals(user2.getName());
        assert(user1.getEmail()).equals(user2.getEmail());
    }
}