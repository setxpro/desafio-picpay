package com.setxpro.picpay.services;

import com.setxpro.picpay.domain.EmailSendUseCase;
import com.setxpro.picpay.domain.user.User;
import com.setxpro.picpay.dtos.TransactionDTO;
import com.setxpro.picpay.repositories.TransactionRepository;
import com.setxpro.picpay.domain.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionService {

  //  private EmailSendUseCase emailSendUseCase;

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository repository;
    // Requisição

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private NotificationService notificationService;


    public Transaction createTransaction(TransactionDTO transaction) throws Exception {
        User sender = this.userService.findUserById(transaction.senderId());
        User receiver = this.userService.findUserById(transaction.receiverId());

        userService.validateTransaction(sender, transaction.value()); // Se a validação não retornar nenhuma exceção, segue...


        // OBS.: Validação comentada para continuar o fluxo da aplicação, pois a api fornecida não é válida.


        // Se não está autorizado.

        boolean isAuthorized = this.authorizationService.authorizationTransaction(sender, transaction.value());
        if(!isAuthorized) {
            throw new Exception("Transação não autorizada.");
       }

        // Se Autorizado.

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transaction.value());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimestamp(LocalDateTime.now());

        // Save database
        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        receiver.setBalance(receiver.getBalance().add(transaction.value()));

        // Persisted data on database
        repository.save(newTransaction);
        userService.saveUser(sender);
        userService.saveUser(receiver);

        // Send notifications about successfully transaction
        this.notificationService.sendNotification(sender, "Transação realizada com sucesso!");
        this.notificationService.sendNotification(receiver, "Transação recebida com sucesso!");

        // Interface
     //   emailSendUseCase.sendEmail("Transação realizada "+ "no valor de R$"+ transaction.value() + "para " + receiver.getFirstName() +" "+ receiver.getLastName() + " com sucesso!", sender.getEmail(), "Transação.", transaction.value(), sender.getFirstName());
     //   emailSendUseCase.sendEmail("Transação recebida com sucesso!", receiver.getEmail(), "Transação.", transaction.value(), receiver.getFirstName());

        // Sem interface
        ServiceEmail.Sender("Transação realizada "+ "no valor de R$"+ transaction.value() + "para " + receiver.getFirstName() +" "+ receiver.getLastName() + " com sucesso!", sender.getEmail(), "Transação.", transaction.value(), sender.getFirstName());
        ServiceEmail.Sender("Transação recebida com sucesso!", receiver.getEmail(), "Transação.", transaction.value(), receiver.getFirstName());
        return newTransaction;

    }


}
