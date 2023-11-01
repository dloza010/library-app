package com.example.libraryapp.repositories;

import com.example.libraryapp.entity.Wishlist.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishListRepository extends JpaRepository<WishList, Long> {

}
