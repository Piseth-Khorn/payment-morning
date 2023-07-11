package com.selfpayment.paymentmorning.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentMorningService {
  public String getWelcome() {
    return "Welcome to payment morning.";
  }
}
