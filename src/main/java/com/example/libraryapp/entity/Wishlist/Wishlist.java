package com.example.libraryapp.entity.Wishlist;

import com.example.libraryapp.entity.Book.Book_Details;
import com.example.libraryapp.entity.Client.Client;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne()
    @JoinColumn(name="userId")
    private Client client;

    @ManyToOne()
    @JoinColumn(name="bookId")
    private Book_Details book_details;

    public Wishlist() {
    }

    public Wishlist(Long id, Client client, Book_Details book_details) {
        this.id = id;
        this.client = client;
        this.book_details = book_details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wishlist wishlist = (Wishlist) o;
        return id.equals(wishlist.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, book_details);
    }

    public Long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Book_Details getBook_details() {
        return book_details;
    }

    public void setBook_details(Book_Details book_details) {
        this.book_details = book_details;
    }
}
