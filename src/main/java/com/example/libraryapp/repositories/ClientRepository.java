package com.example.libraryapp.repositories;

import com.example.libraryapp.entity.Client.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
