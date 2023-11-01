package com.example.libraryapp.repositories;

import com.example.libraryapp.entity.Book.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long>{
}
