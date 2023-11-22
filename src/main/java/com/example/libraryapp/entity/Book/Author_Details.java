package com.example.libraryapp.entity.Book;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Flow;

@Entity
public class Author_Details {
    //Instance Variables
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long author_id;
    private String first_name;
    private String last_name;
    private String biography;

    //constructors
    public Author_Details() {
    }

    public Author_Details(String first_name, String last_name, String biography) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.biography = biography;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author_Details that = (Author_Details) o;
        return author_id == that.author_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(author_id);
    }

    public long getAuthor_id() {
        return author_id;
    }

//    public void setAuthor_id(long author_id) {
//        this.author_id = author_id;
//    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
}
