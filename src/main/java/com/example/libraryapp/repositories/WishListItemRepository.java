package com.example.libraryapp.repositories;

import com.example.libraryapp.entity.WishListItem.WishListItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishListItemRepository extends JpaRepository<WishListItem, Long> {

}
