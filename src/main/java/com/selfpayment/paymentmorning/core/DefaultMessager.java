package com.selfpayment.paymentmorning.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DefaultMessager implements Messager {
    @Override
    public void send(String message) {
        log.info("Message to send [{}]", message);
    }
}
