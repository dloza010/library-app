package com.example.libraryapp.entity.Book;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.concurrent.Flow;
@Entity
public class Publisher {
    //Instance Variables
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long publisher_id;

    private String publisher_name;

    //Constructors
    public Publisher() {
    }

    public Publisher(String publisher_name) {
        //his.publisher_id = publisher_id;
        this.publisher_name = publisher_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publisher publisher = (Publisher) o;
        return publisher_id == publisher.publisher_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(publisher_id);
    }

    public long getPublisher_id() {
        return publisher_id;
    }

//    public void setPublisher_id(long publisher_id) {
//        this.publisher_id = publisher_id;
//    }

    public String getPublisher_name() {
        return publisher_name;
    }

    public void setPublisher_name(String publisher_name) {
        this.publisher_name = publisher_name;
    }
}
