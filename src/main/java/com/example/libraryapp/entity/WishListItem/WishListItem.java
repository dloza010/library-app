package com.example.libraryapp.entity.WishListItem;

import jakarta.persistence.*;

import com.example.libraryapp.entity.Book.Book;
import com.example.libraryapp.entity.WishList.WishList;

@Entity
public class WishListItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemID;

    @ManyToOne
    @JoinColumn(name = "wishListID")
    private WishList wishList;

    @ManyToOne
    @JoinColumn(name = "bookID")
    private Book book;

    public WishListItem() {
    }

    public WishListItem(WishList wishList, Book book) {
        this.wishList = wishList;
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WishListItem that = (WishListItem) o;
        return itemID != null ? itemID.equals(that.itemID) : that.itemID == null;
    }

    @Override
    public int hashCode() {
        return itemID != null ? itemID.hashCode() : 0;
    }

    public Long getItemID() {
        return itemID;
    }

    public void setItemID(Long itemID) {
        this.itemID = itemID;
    }

    public WishList getWishList() {
        return wishList;
    }

    public void setWishList(WishList wishList) {
        this.wishList = wishList;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
