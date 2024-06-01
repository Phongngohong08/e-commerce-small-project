package com.example.project.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class AddPaymentRequest {

    private String provider;
    private String accountNumber;
    private PAYMENTTYPE paymenttype;
}
