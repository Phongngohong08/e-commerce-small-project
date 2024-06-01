package com.example.project.payment;

import com.example.project.appuser.AppUser;
import com.example.project.appuser.AppUserRepository;
import com.example.project.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;
    private final JwtService jwtService;
    private final AppUserRepository appUserRepository;

    public List<String> findAllPaymentMethod() {
        List<PaymentMethod> list =  paymentMethodRepository.findAll();
        return list.stream().map(
                paymentMethod -> "Id: "+ paymentMethod.getId() + ". account number: "
                        + paymentMethod.getAccountNumber() + ". provider: "
                        + paymentMethod.getProvider() + ". paymentType: "
                        + paymentMethod.getPaymentType() + ". appUserId: "
                        + paymentMethod.getAppUser().getId()
        ).toList();
    }

    public void deletePaymentMethod(Integer id) {
        boolean exists = paymentMethodRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Payment method with id " + id + " does not exist");
        }
        paymentMethodRepository.deleteById(id);
    }

    public void addPaymentMethod(
            AddPaymentRequest addPaymentRequest,
            Principal connectedUser) throws IOException {

        var user = (AppUser) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        String userEmail = user.getEmail();
        PaymentMethod paymentMethod = PaymentMethod.builder()
                .appUser(appUserRepository.findByEmail(userEmail).orElseThrow(
                        () -> new IllegalStateException("User not found")))
                .provider(addPaymentRequest.getProvider())
                .accountNumber((addPaymentRequest.getAccountNumber()))
                .paymentType(addPaymentRequest.getPaymenttype())
                .build();
        paymentMethodRepository.save(paymentMethod);
    }
}
