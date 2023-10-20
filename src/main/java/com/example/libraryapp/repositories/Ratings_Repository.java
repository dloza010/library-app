package com.example.libraryapp.repositories;

import com.example.libraryapp.entity.Comments.Ratings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Ratings_Repository extends JpaRepository<Ratings, Long> {
}
