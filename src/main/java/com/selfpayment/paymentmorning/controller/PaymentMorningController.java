package com.selfpayment.paymentmorning.controller;

import com.itextpdf.text.DocumentException;
import com.selfpayment.paymentmorning.service.AuthToken;
import com.selfpayment.paymentmorning.service.PaymentMorningService;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/v1/payment-morning")
@RequiredArgsConstructor
public class PaymentMorningController {
  private final PaymentMorningService paymentMorningService;

  @GetMapping
  public ResponseEntity<String> welcome(@RequestHeader("Authorization") String token) {
    return new ResponseEntity<>(paymentMorningService.getWelcome(token), HttpStatus.OK);
  }
}
