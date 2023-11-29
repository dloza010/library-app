package com.example.libraryapp.repositories;

import com.example.libraryapp.entity.Client.Client;
import com.example.libraryapp.entity.Comments.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Comments_Repository extends JpaRepository<Comments, Long> {
    @Query("SELECT c FROM  Comments c"
            + " WHERE c.book_details = ?1")
    public List<Comments> findCommentsByBookId(long id);

    @Query(value = "SELECT comment FROM Comments WHERE book_id = ?1", nativeQuery = true)
    public List<Comments> findCommentsByBookId1(long id);
}
