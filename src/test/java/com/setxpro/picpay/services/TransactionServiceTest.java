package com.setxpro.picpay.services;

import com.setxpro.picpay.domain.user.User;
import com.setxpro.picpay.domain.user.UserType;
import com.setxpro.picpay.dtos.TransactionDTO;
import com.setxpro.picpay.repositories.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TransactionServiceTest {


    @Mock // Cria uma instancia vazia
    private UserService userService;

    @Mock
    private TransactionRepository repository;
    // Requisição

    @Mock
    private AuthorizationService authorizationService;

    @Mock
    private NotificationService notificationService;

    @Autowired
    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach // Executa antes dos testes unitários
    void setup() {
        // Iniciar os moks
        MockitoAnnotations.initMocks(this); // this -> referente a class de testes
    }

    @Test
    @DisplayName("Should create transaction successfully when everything is ok")
    void createTransactionSuccessfully() throws Exception {
        User sender = new User(1L, "Miguel", "Rocha", "12345678910", "miguel@mail.com", "123456", new BigDecimal(10), UserType.COMMON);
        User receiver = new User(2L, "Heitor", "Rocha", "12345678915", "heitor@mail.com", "123456", new BigDecimal(20), UserType.COMMON);

        // when - vem do mockito
        when(userService.findUserById(1L)).thenReturn(sender);
        when(userService.findUserById(2L)).thenReturn(receiver);

        // Authorize Service
        // Return transaction
        when(authorizationService.authorizationTransaction(any(), any())).thenReturn(true);

        // Create object transaction

        TransactionDTO request = new TransactionDTO(new BigDecimal(10), 1L, 2L);
        transactionService.createTransaction(request);

        // Verify transaction -> save in database
        verify(repository, times(1)).save(any());

        // Update users after transaction
        // sender
        sender.setBalance(new BigDecimal(0)); // why he had $10.00
        verify(userService, times(1)).saveUser(sender);

        // receiver
        receiver.setBalance(new BigDecimal(30));
        verify(userService, times(1)).saveUser(receiver);

        // verify if notify have be sent
        verify(notificationService, times(1)).sendNotification(sender, "Transação realizada com sucesso!");
        verify(notificationService, times(1)).sendNotification(receiver, "Transação recebida com sucesso!");
    }

    @Test
    @DisplayName("Should throw Exception when Transaction is not allowed")
    void createTransactionFail() throws Exception {
        User sender = new User(1L, "Miguel", "Rocha", "12345678910", "miguel@mail.com", "123456", new BigDecimal(10), UserType.COMMON);
        User receiver = new User(2L, "Heitor", "Rocha", "12345678915", "heitor@mail.com", "123456", new BigDecimal(20), UserType.COMMON);

        // when - vem do mockito
        when(userService.findUserById(1L)).thenReturn(sender);
        when(userService.findUserById(2L)).thenReturn(receiver);

        // Return transaction
        when(authorizationService.authorizationTransaction(any(), any())).thenReturn(false);

        // or RunTimeException
        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            // find create transaction service
            TransactionDTO request = new TransactionDTO(new BigDecimal(10), 1L, 2L);
            transactionService.createTransaction(request);
        });

        Assertions.assertEquals("Transação não autorizada.", thrown.getMessage());
    }
}