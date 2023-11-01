package com.example.libraryapp.repositories;

import com.example.libraryapp.entity.Cart.Cart_Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Cart_Item_Repository extends JpaRepository<Cart_Item, Long> {
}
