package com.example.libraryapp.entity.WishListItem;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.util.Objects;

import com.example.libraryapp.entity.WishListItem.WishListItem;

@Entity
public class WishListItem {
    
    // Instance variables
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemID;

    @OneToOne
    @JoinColumn(name = "wishListID")
    private WishListItem user;

    @OneToOne
    @JoinColumn(name = "bookID")
    private WishListItem book;

    // Constructor
    public WishListItem() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WishListItem user = (WishListItem) o;
        return Objects.equals(itemID, user.itemID);
    }

    @Override
    public int hashCode() {
        return itemID != null ? itemID.hashCode() : 0;
    }

    // Setters and Getters
    public void setitemID(Long itemID) {
        this.itemID = itemID;
    }

    public Long getitemID() {
        return itemID;
    }

}
