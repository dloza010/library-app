package com.example.libraryapp.repositories;

import com.example.libraryapp.entity.Book.Book_Author_Intermediate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Book_Author_Intermediate_Repository extends JpaRepository<Book_Author_Intermediate, Long> {
    //return author id from ISBN
    @Query("SELECT c.author_details.author_id  FROM  Book_Author_Intermediate c"
            + " WHERE c.book_details.ISBN = ?1")
    public org.hibernate.mapping.List findAuthorId(long ISBN);
}
