package com.selfpayment.paymentmorning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class PaymentMorning {

  public static void main(String[] args) {
    SpringApplication.run(PaymentMorning.class, args);
  }
}
