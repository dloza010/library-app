package com.example.libraryapp.repositories;

import com.example.libraryapp.entity.Comments.Comments;
import com.example.libraryapp.entity.Comments.Ratings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Ratings_Repository extends JpaRepository<Ratings, Long> {
    @Query("SELECT r.rating  FROM  Ratings r"
            + " WHERE r.book_details.ISBN = ?1")
    public List<Double> findByBookId(long ISBN);

}
