package com.example.libraryapp.repositories;

import com.example.libraryapp.entity.Book.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    //return publisher id from ISBN
    @Query("SELECT c.publisher.publisher_id  FROM  Book_Details c"
            + " WHERE c.ISBN = ?1")
    public org.hibernate.mapping.List findPublisherId(long ISBN);
}
