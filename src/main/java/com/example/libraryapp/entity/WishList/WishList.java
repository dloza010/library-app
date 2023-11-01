package com.example.libraryapp.entity.WishList;

import com.example.libraryapp.entity.Client.Client;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.util.Objects;

@Entity
public class WishList {

    // Instance variables
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long wishListID;

    @OneToOne
    @JoinColumn(name = "clientID", referencedColumnName = "id")
    private Client user;
    private Long userID;

    // Constructor
    public WishList() {

    }
    public WishList(Client user) {
        this.user = user;
        this.userID = user.getId();
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
    public void setWishListID(Long wishListID) {
        this.wishListID = wishListID;
    }

    public Long getWishListID() {
        return wishListID;
    }
    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getUserID() {
        return userID;
    }
}