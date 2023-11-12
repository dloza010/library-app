package com.example.libraryapp.repositories;

import com.example.libraryapp.entity.Client.Client;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
//return client data for an admin

    @Query("SELECT c FROM  Client c"
            + " WHERE c.isAdmin = TRUE AND c.id = ?1")
    public List<Client> findAdminById(long id);

    @Transactional
    @Modifying
    @Query("update Client c set c.homeAddress = ?1 where c.username = ?2")
    void updateHomeAddress(String homeAddress, String username);

    @Transactional
    @Modifying
    @Query("update Client c set c.name = ?1 where c.username = ?2")
    void updateName(String name, String username);

    @Transactional
    @Modifying
    @Query("update Client c set c.username = ?1 where c.username = ?2")
    void updateUsername(String newUsername, String username);

    @Transactional
    @Modifying
    @Query("update Client c set c.password = ?1 where c.username = ?2")
    void updatePassword(String password, String username);


    Client findByUsername(String username);
}
