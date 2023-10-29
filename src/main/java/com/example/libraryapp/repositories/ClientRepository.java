package com.example.libraryapp.repositories;

import com.example.libraryapp.entity.Client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
//return client data for an admin

    @Query("SELECT c FROM  Client c"
            + " WHERE c.isAdmin = TRUE AND c.id = ?1")
    public List<Client> findAdminById(long id);
}
