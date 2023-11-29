package com.example.libraryapp.repositories;

import com.example.libraryapp.entity.Comments.Comments;
import com.example.libraryapp.entity.Comments.Ratings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Ratings_Repository extends JpaRepository<Ratings, Long> {
    @Query("SELECT r FROM  Ratings r"
            + " WHERE r.book_details = ?1")
    public List<Ratings> findRatingsByBookId(long id);

    @Query(value = "SELECT rating FROM Ratings WHERE book_id = ?1", nativeQuery = true)
    public List<Ratings> findRatingsByBookId1(long id);
}
