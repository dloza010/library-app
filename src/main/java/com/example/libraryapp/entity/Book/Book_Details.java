package com.example.libraryapp.entity.Book;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Flow;

@Entity
public class Book_Details {
    //Instance Variables
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    //To impose data type definition of varchar(17) in the database
    //@Column(columnDefinition="varchar(17)")
    @Column(nullable = false)
    private long ISBN;

    private String title;
    private String description;
    private double price;
    private String genre;
    private int year_published;
    private int copies_sold;
    //private int created_at;
    @ManyToOne()
    @JoinColumn(name="publisher_id")
    private Publisher publisher;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book_Details book = (Book_Details) o;
        return Objects.equals(ISBN, book.ISBN);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ISBN);
    }

    //Constructors
    public Book_Details() {

    }


    public Book_Details(long ISBN, String title, String description, double price, String genre, int year_published, int copies_sold, Publisher publisher) {
        this.ISBN = ISBN;
        //this.first_name = first_name;
        //this.last_name = last_name;
        this.title = title;
        this.description = description;
        this.price = price;
        this.genre = genre;
        this.year_published = year_published;
        this.copies_sold = copies_sold;
        //this.created_at = created_at;
        this.publisher = publisher;
    }

    //Setters and Getters

    public long getISBN() {
        return ISBN;
    }

    public void setISBN(long ISBN) {
        this.ISBN = ISBN;
    }

    public String setTitle() {
        return title;
    }
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear_published() {
        return year_published;
    }

    public void setYear_published(int year_published) {
        this.year_published = year_published;
    }

    public int getCopies_sold() {
        return copies_sold;
    }

    public void setCopies_sold(int copies_sold) {
        this.copies_sold = copies_sold;
    }

//    @ManyToOne(optional = false)
//    private Book_Author_Intermediate book_author_intermediates;
//
//    public Book_Author_Intermediate getBook_author_intermediates() {
//        return book_author_intermediates;
//    }
//
//    public void setBook_author_intermediates(Book_Author_Intermediate book_author_intermediates) {
//        this.book_author_intermediates = book_author_intermediates;
//    }
}
