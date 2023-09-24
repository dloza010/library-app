package com.example.libraryapp.repositories;

import com.example.libraryapp.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Client, Long> {

}
