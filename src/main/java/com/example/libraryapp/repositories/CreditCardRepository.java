package com.example.libraryapp.repositories;

import com.example.libraryapp.entity.Client.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

    CreditCard findByCardNumber (String cardNumber);
}
