package com.setxpro.picpay.dtos;

import com.setxpro.picpay.domain.user.User;
import com.setxpro.picpay.domain.user.UserType;

import java.math.BigDecimal;

public record UserDTO(String firstName, String lastName, String document, BigDecimal balance, String email, String password, UserType userType) {
}
