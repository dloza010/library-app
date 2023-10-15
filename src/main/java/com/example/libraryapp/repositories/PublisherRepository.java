package com.example.libraryapp.repositories;

import com.example.libraryapp.entity.Book.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {

}
