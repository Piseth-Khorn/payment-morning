package com.selfpayment.paymentmorning.core.thread;

import com.selfpayment.paymentmorning.core.Messager;

public class MessageQueue extends MessageThreading {
    public MessageQueue(Messager messager) {
        super(messager);
    }

    public void addMessageQueue(String message) {
        if (this.shuttingDown || this.messageTerminated) return;
        try {
            this.messageQueue.put(message);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Unexpected interruption", e);
        }
    }

    public void shutDownQueue() throws InterruptedException {
        this.shuttingDown = true;
        this.messageQueue.put(SHUTDOWN_REQ);
    }
}
