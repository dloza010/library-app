package com.example.libraryapp.entity.Comments;

import com.example.libraryapp.entity.Book.Book_Details;
import com.example.libraryapp.entity.Client.Client;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Objects;

@Entity
public class Ratings {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long rating_id;

    @ManyToOne()
    @JoinColumn(name="userId", referencedColumnName = "id")
    private Client client;

    @ManyToOne()
    @JoinColumn(name="bookId", referencedColumnName = "ISBN")
    private Book_Details book_details;

    private int rating;

    @CreationTimestamp
    @Column(name="timestamp", nullable = false, updatable = false, insertable = false)
    private String timestamp;

    public Ratings() {
    }

    public Ratings(Client client, Book_Details book_details, int rating, String timestamp) {
        this.client = client;
        this.book_details = book_details;
        this.rating = rating;
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ratings ratings = (Ratings) o;
        return rating_id == ratings.rating_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rating_id);
    }

    public long getRating_id() {
        return rating_id;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getTimestamp() {
        return timestamp;
    }

}
