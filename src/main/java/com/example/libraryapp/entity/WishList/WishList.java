package com.example.libraryapp.entity.WishList;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.libraryapp.entity.Client.Client;
import com.example.libraryapp.entity.WishListItem.WishListItem;
import com.example.libraryapp.entity.Book.Book;

@Entity
public class WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long wishListID;

    @ManyToOne
    @JoinColumn(name = "clientID", referencedColumnName = "id")
    private Client user;

    private Long userID;

    private String name;

    @OneToMany(mappedBy = "wishList", cascade = CascadeType.ALL)
    private List<WishListItem> wishListItems;

    public WishList() {
    }

    public WishList(Client user, String name) {
        this.user = user;
        this.userID = user.getId();
        this.name = name;
        this.wishListItems = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WishList wishList = (WishList) o;
        return Objects.equals(wishListID, wishList.wishListID);
    }

    @Override
    public int hashCode() {
        return wishListID != null ? wishListID.hashCode() : 0;
    }

    public Long getWishListID() {
        return wishListID;
    }

    public void setWishListID(Long wishListID) {
        this.wishListID = wishListID;
    }

    public Client getUser() {
        return user;
    }

    public void setUser(Client user) {
        this.user = user;
        this.userID = user.getId();
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<WishListItem> getWishListItems() {
        return wishListItems;
    }

    public void setWishListItems(List<WishListItem> wishListItems) {
        this.wishListItems = wishListItems;
    }

    public void addBook(Book book) {
        if (wishListItems.stream().noneMatch(item -> item.getBook().equals(book))) {
            WishListItem wishListItem = new WishListItem(this, book);
            wishListItems.add(wishListItem);
        }
    }

    public void removeBook(Book book) {
        Optional<WishListItem> wishListItemToRemove = wishListItems.stream()
                .filter(item -> item.getBook().equals(book))
                .findFirst();

        wishListItemToRemove.ifPresent(wishListItems::remove);
    }

    public List<Book> getBooks() {
        return wishListItems.stream()
                .map(WishListItem::getBook)
                .collect(Collectors.toList());
    }
}
