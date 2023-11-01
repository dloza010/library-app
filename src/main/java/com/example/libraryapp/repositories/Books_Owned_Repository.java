package com.example.libraryapp.repositories;

import com.example.libraryapp.entity.Client.Books_Owned;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Books_Owned_Repository extends JpaRepository<Books_Owned, Long> {
}
