package com.example.libraryapp.entity.Comments;

import com.example.libraryapp.entity.Book.Book_Details;
import com.example.libraryapp.entity.Client.Books_Owned;
import com.example.libraryapp.entity.Client.Client;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
public class Comments {
    @Id
    private long comment_id;

    @ManyToOne()
    @JoinColumn(name="userId")
    private Client client;

    @ManyToOne()
    @JoinColumn(name="bookId")
    private Book_Details book_details;

    private String comment;

    @CreationTimestamp
    @Column(name="timestamp", nullable = false, updatable = false, insertable = false)
    private Timestamp timestamp;

    public Comments() {
    }

    public Comments(long comment_id, Client client, Book_Details book_details, String comment) {
        this.comment_id = comment_id;
        this.client = client;
        this.book_details = book_details;
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comments comments = (Comments) o;
        return comment_id == comments.comment_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(comment_id);
    }

    public long getComment_id() {
        return comment_id;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

}
