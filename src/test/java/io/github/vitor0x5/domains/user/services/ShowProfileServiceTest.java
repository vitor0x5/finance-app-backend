package io.github.vitor0x5.domains.user.services;

import io.github.vitor0x5.domains.user.dto.SignUpDTO;
import io.github.vitor0x5.domains.user.dto.UserDataDTO;
import io.github.vitor0x5.domains.user.repositories.fakes.FakeUsersRepository;
import io.github.vitor0x5.domains.user.utils.mocks.UserMocksFactory;
import io.github.vitor0x5.shared.encoder.fakes.FakeEncoder;
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

        user1 = UserMocksFactory.mockUser1SignUpDTO();
        repository.save(SignUpDTO.toAppUser(user1));
    }

    @Test
    void testShowProfileWithValidEmail() {
        UserDataDTO profileData = showProfileService.execute(user1.getEmail());
        UserDataDTO expectedProfileData = new UserDataDTO(user1.getName(), user1.getEmail());
        assertThatUsersAreEqual(expectedProfileData, profileData);
    }

    @Test
    void testShowProfileWithInvalidEmail() {
        try {
            showProfileService.execute("user2@email.com");
        } catch (RuntimeException e) {
            assertThat(e).isInstanceOf(NotFoundException.class);
        }


    }

    private void assertThatUsersAreEqual(UserDataDTO user1, UserDataDTO user2) {
        assert(user1.getName()).equals(user2.getName());
        assert(user1.getEmail()).equals(user2.getEmail());
    }
}