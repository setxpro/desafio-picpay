package com.setxpro.picpay.domain;

import java.math.BigDecimal;

public interface EmailSendUseCase {
    void sendEmail(String body, String to, String subject, BigDecimal value, String name);
}
