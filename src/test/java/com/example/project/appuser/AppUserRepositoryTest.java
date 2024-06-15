package com.example.project.appuser;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
class AppUserRepositoryTest {

    @Autowired
    private AppUserRepository underTest;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void findByEmail() {
        // given
        String email = "test@gmail.com";
        AppUser appUser = new AppUser(
                "Test",
                email,
                "password",
                Role.USER
        );
        entityManager.persist(appUser);
        entityManager.flush();

        // when
        AppUser result = underTest.findByEmail(email).orElseThrow(
                () -> new IllegalStateException("User not found")
        );

        // then
        assertThat(result).isEqualTo(appUser);
    }
}