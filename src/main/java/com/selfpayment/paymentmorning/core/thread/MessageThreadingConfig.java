package com.selfpayment.paymentmorning.core.thread;

import com.selfpayment.paymentmorning.core.Messager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class MessageThreadingConfig {
    @Bean
    public MessageQueue messageThreading(Messager messager) {
        var messageQueue = new MessageQueue(messager);
        messageQueue.start();
        return messageQueue;
    }
}
