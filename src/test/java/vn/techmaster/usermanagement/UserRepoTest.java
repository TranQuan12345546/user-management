package vn.techmaster.usermanagement;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import vn.techmaster.usermanagement.entity.User;
import vn.techmaster.usermanagement.repository.UserRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepoTest {
    @Autowired
    private UserRepository userRepository;
    @Test
    void getAllUser() {
        List<User> users = userRepository.findAll();
        assertThat(users.size()).isEqualTo(62);
    }

}
