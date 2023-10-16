package com.example.libraryapp.entity.WishList;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.util.Objects;

import com.example.libraryapp.entity.WishList.WishList;

@Entity
public class WishList {
    
    // Instance variables
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long wishListID;

    @OneToOne
    @JoinColumn(name = "clientID")
    private Long user;

    // Constructor
    public WishList() {

    }
    public WishList(Long user) {
        this.user = user;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WishList user = (WishList) o;
        return Objects.equals(wishListID, user.wishListID);
    }

    @Override
    public int hashCode() {
        return wishListID != null ? wishListID.hashCode() : 0;
    }

    // Setters and Getters
    public void setwishListID(Long wishListID) {
        this.wishListID = wishListID;
    }

    public Long getwishListID() {
        return wishListID;
    }

}
