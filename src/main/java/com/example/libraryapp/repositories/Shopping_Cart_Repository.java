package com.example.libraryapp.repositories;

import com.example.libraryapp.entity.Cart.Shopping_Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Shopping_Cart_Repository extends JpaRepository<Shopping_Cart, Long> {
}
