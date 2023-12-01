package com.example.libraryapp.repositories;

import com.example.libraryapp.entity.Book.Book_Details;
import com.example.libraryapp.entity.Client.Client;
import com.example.libraryapp.entity.Comments.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Comments_Repository extends JpaRepository<Comments, Long> {

    @Query("SELECT c.comment  FROM  Comments c"
            + " WHERE c.book_details.ISBN = ?1")
    public List<String> findByBookId(long ISBN);

}
