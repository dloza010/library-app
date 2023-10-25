package com.example.libraryapp.entity.Client;

import com.example.libraryapp.entity.Book.Book_Details;
import com.example.libraryapp.entity.Book.Publisher;
import com.example.libraryapp.entity.Comments.Comments;
import com.example.libraryapp.entity.Comments.Ratings;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Books_Owned {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne()
    @JoinColumn(name="user_id")
    private Client client;

    @ManyToOne()
    @JoinColumn(name="ISBN")
    private Book_Details book_details;

    @OneToOne
    @JoinColumn(name="comment_id")
    private Comments comments;

    @OneToOne
    @JoinColumn(name="rating_id")
    private Ratings ratings;

    public Books_Owned() {
    }

    public Books_Owned(Client client, Book_Details book_details, Comments comments, Ratings ratings) {
        this.client = client;
        this.book_details = book_details;
        this.comments = comments;
        this.ratings = ratings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Books_Owned that = (Books_Owned) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, book_details, comments, ratings);
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

    public Comments getComments() {
        return comments;
    }

    public void setComments(Comments comments) {
        this.comments = comments;
    }

    public Ratings getRatings() {
        return ratings;
    }

    public void setRatings(Ratings ratings) {
        this.ratings = ratings;
    }
}
