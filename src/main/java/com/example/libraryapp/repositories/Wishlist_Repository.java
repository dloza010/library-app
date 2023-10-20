package com.example.libraryapp.repositories;

import com.example.libraryapp.entity.Wishlist.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Wishlist_Repository extends JpaRepository<Wishlist, Long> {
}
