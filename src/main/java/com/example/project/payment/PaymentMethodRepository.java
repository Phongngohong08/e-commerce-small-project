package com.example.project.payment;

import com.example.project.token.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Integer> {

    @Query(value = """
      select p from PaymentMethod p inner join AppUser u
      on p.appUser.id = u.id
      """)
    List<PaymentMethod> findAllPaymentMethodByAppUserId(Integer id);
}
