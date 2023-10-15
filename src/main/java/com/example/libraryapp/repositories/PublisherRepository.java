package com.example.libraryapp.repositories;

import com.example.libraryapp.entity.Book.Book_Details;
import com.example.libraryapp.entity.Book.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book_Details, Long> {

}
