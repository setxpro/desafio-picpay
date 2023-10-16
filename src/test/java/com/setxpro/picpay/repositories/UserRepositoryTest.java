package com.setxpro.picpay.repositories;

import com.setxpro.picpay.domain.user.User;
import com.setxpro.picpay.domain.user.UserType;
import com.setxpro.picpay.dtos.UserDTO;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;


import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Optional;

// Exemplo de testes feito com uma interface
// OBS.: O JPA já testa automaticamente sendo assim:
// Princípio -> Isto está funcionando.
// Apenas onde eu tive que escrever a lógica
// exemplo -> @Query -> "SELECT * FROM ..."
// Quando realizado uma query diretamente.

@DataJpaTest // indica para o spring que uma class de teste q irá testar uma interface JPA
@ActiveProfiles("test") // use test file
class UserRepositoryTest {

    // use database test H2
    // Testar se tem um usuário ou não

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager entityManager;




    @Test
    @DisplayName("Should get user from db when user not exists") // Description
    void findUserByDocumentSuccess() {

        // CREATE USER
        String document = "98758558520";
        String email = "patrickpqdt87289@gmail.com";
        String password = "123654";
        UserDTO data = new UserDTO("Patrick", "Test", document, new BigDecimal(10), email, password, UserType.COMMON);
        this.createUser(data);

        // FIND USER BY DOCUMENT

        Optional<User> result = this.userRepository.findUserByDocument(document);

        // Conferindo o resultado do test

        // isPresent() do próprio Optional... assim como o isEmpty
        assertThat(result.isPresent()).isTrue();

    }

    @Test
    @DisplayName("Should not get user successfully db") // Description
    void findUserByDocumentFail() {
        // CREATE USER
        String document = "98758558525";

        // FIND USER BY DOCUMENT

        Optional<User> result = this.userRepository.findUserByDocument(document);

        // Conferindo o resultado do test

        // isEmpty() do próprio Optional... assim como o isPresent
        assertThat(result.isEmpty()).isTrue();
    }

    // Create user to test database
    private User createUser(UserDTO data) {
        User newUser = new User(data);
        this.entityManager.persist(newUser);
        return newUser;
    }
}