package com.example.libraryapp.repositories;

import com.example.libraryapp.entity.Comments.Ratings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Ratings_Repository extends JpaRepository<Ratings, Long> {
    List<Ratings> findRatingsByBook_details(long bookId);
}
