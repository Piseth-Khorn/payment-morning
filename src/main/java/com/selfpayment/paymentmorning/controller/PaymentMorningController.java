package com.selfpayment.paymentmorning.controller;

import com.selfpayment.paymentmorning.service.PaymentMorningService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/payment-morning")
@RequiredArgsConstructor
public class PaymentMorningController {
  private final PaymentMorningService paymentMorningService;

  @GetMapping
  public ResponseEntity<String> welcome() {
    return new ResponseEntity<>(paymentMorningService.getWelcome(), HttpStatus.OK);
  }
}
