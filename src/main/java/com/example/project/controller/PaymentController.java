package com.example.project.controller;

import com.example.project.payment.AddPaymentRequest;
import com.example.project.payment.PaymentMethod;
import com.example.project.payment.PaymentMethodService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentMethodService paymentMethodService;

    @GetMapping(path = "/findAllPaymentMethod")
    @PreAuthorize("hasAuthority('user:read')")
    public List<String> findAllPaymentMethod() {
        return paymentMethodService.findAllPaymentMethod();
    }

    @DeleteMapping(path = "/delete/{id}")
    @PreAuthorize("hasAuthority('user:delete')")
    public void deletePaymentMethod(@PathVariable("id") Integer id) {
        paymentMethodService.deletePaymentMethod(id);
    }

    @PostMapping(path = "/addPaymentMethod")
    @PreAuthorize("hasAuthority('user:update')")
    public void addPaymentMethod(@RequestBody AddPaymentRequest addPaymentRequest,
                                 Principal connectedUser) throws IOException {
        paymentMethodService.addPaymentMethod(addPaymentRequest, connectedUser);
    }
}
