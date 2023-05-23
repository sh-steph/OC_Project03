package com.openclassrooms.occhatop.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import com.openclassrooms.occhatop.models.authentication.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    // test methods go below
    @Test
    public void testCreateUser() {
        User user = new User();
        user.setEmail("testOC@gmail.com");
        user.setPassword("test1234");
        user.setName("OpenClass");

        User savedUser = userRepository.save(user);

        User existUser = entityManager.find(User.class, savedUser.getId());
        System.out.print(user);

        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
        assertThat(existUser.getCreatedAt()).isNotNull();
    }
}