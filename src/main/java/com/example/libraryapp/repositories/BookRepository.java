package com.example.libraryapp.repositories;

import com.example.libraryapp.entity.Book.Author_Details;
import com.example.libraryapp.entity.Book.Book_Author_Intermediate;
import com.example.libraryapp.entity.Book.Book_Details;
import com.example.libraryapp.entity.Book.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book_Details, Long>{
}
