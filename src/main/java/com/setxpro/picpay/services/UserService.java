package com.setxpro.picpay.services;

import com.setxpro.picpay.domain.user.User;
import com.setxpro.picpay.domain.user.UserType;
import com.setxpro.picpay.dtos.UserDTO;
import com.setxpro.picpay.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        // Se o tipo do usuário for igual a  lojista
        if (sender.getUserType() != UserType.MERCHANT) {
            throw new Exception("Usuário do tipo logista não está autorizado para realizar a transação.");
        }
        if (sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("Saldo insuficiente.");
        }
    }

    public User findUserById(Long id) throws Exception {
       return this.repository.findUserById(id).orElseThrow(() -> new Exception("Usuário não encontrado."));
    }

    public User createUser(UserDTO data) {
        User newUser = new User(data);
        this.saveUser(newUser);
        return newUser;
    }

    public List<User> getAllUsers() {
        return this.repository.findAll();
    }

    // Persisted user on database.
    public void saveUser(User user) {
        this.repository.save(user);
    }
}
