package com.example.libraryapp.repositories;

import com.example.libraryapp.entity.Comments.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Comments_Repository extends JpaRepository<Comments, Long> {
}
