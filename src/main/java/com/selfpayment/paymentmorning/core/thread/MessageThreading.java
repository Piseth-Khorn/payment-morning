package com.selfpayment.paymentmorning.core.thread;

import com.selfpayment.paymentmorning.core.Messager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Slf4j
@RequiredArgsConstructor
public abstract class MessageThreading extends Thread {
    private final Messager messager;
    public static final String SHUTDOWN_REQ = "SHUTDOWN";
    protected volatile boolean shuttingDown;
    protected volatile boolean messageTerminated;
    protected BlockingQueue<String> messageQueue = new ArrayBlockingQueue<>(100);

    @Override
    public void run() {
        try {
            String message;
            while ((message = messageQueue.take()) != SHUTDOWN_REQ) {
                log.info("Starting sending message in thread id {}", Thread.currentThread().threadId());
                this.messager.send(message);
            }

        } catch (InterruptedException iex) {
            log.warn("Thread is interrupted!", iex);
            Thread.currentThread().interrupt();
        } finally {
            this.messageTerminated = true;
        }
    }

    public abstract void addMessageQueue(String message);

    public abstract void shutDownQueue() throws InterruptedException;

}
