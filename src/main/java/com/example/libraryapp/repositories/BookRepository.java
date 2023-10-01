package com.example.libraryapp.repositories;

import com.example.libraryapp.entity.Book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
