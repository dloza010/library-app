package com.example.libraryapp.repositories;

import com.example.libraryapp.entity.WishList.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Wishlist_Repository extends JpaRepository<WishList, Long> {
}
