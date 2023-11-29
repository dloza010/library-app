package com.example.libraryapp.repositories;

import com.example.libraryapp.entity.Comments.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Comments_Repository extends JpaRepository<Comments, Long> {
    List<Comments> findCommentsByBook_details(long bookId);
}
