package com.setxpro.picpay.repositories;

import com.setxpro.picpay.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Method to find one user by document
    // The return "Optional", because can or not user registered
    Optional<User> findUserByDocument(String document);

    Optional<User> findUserById(Long id);

}
