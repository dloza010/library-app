package com.example.libraryapp.entity.Book;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Flow;

@Entity
public class Book_Author_Intermediate {
    //Instance Variables
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne()
    @JoinColumn(name="author_id", nullable = false)
    private Author_Details author_details;

    @ManyToOne()
    @JoinColumn(name="ISBN", nullable = false)
    private Book_Details book_details;

    //constructors
    public Book_Author_Intermediate() {
    }

    public Book_Author_Intermediate(Author_Details author_details, Book_Details book_details) {
        this.author_details = author_details;
        this.book_details = book_details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book_Author_Intermediate that = (Book_Author_Intermediate) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public long getId() {
        return id;
    }

//    public void setId(long id) {
//        this.id = id;
//    }

    public Author_Details getAuthor_details() {
        return author_details;
    }

    public void setAuthor_details(Author_Details author_details) {
        this.author_details = author_details;
    }

    public Book_Details getBook_details() {
        return book_details;
    }

    public void setBook_details(Book_Details book_details) {
        this.book_details = book_details;
    }
}

//cascade={CascadeType.PERSIST, CascadeType.REMOVE}