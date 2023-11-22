package com.example.libraryapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.libraryapp.entity.WishList.WishList;

public interface WishListRepository extends JpaRepository<WishList, Long> {

    WishList findByUserAndName(com.example.libraryapp.entity.Client.Client client, String name);

}
