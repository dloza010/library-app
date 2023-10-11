package com.example.libraryapp.entity.Book;

import com.example.libraryapp.entity.Client.Client;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Book {

    //Instance Variables
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private int quantity;
    private int releaseYear;
    private String author;
    @ManyToOne
    @JoinColumn(name="client_id", referencedColumnName = "id")
    private Client client;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    //Constructor
    public Book() {

    }

    public Book(String title, int quantity, int releaseYear, String author, Client client) {
        this.title = title;
        this.quantity = quantity;
        this.releaseYear = releaseYear;
        this.author = author;
        this.client = client;
    }

    //Setters and Getters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
