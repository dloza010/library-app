package com.example.libraryapp.entity.Client;

import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@Entity
public class Client {

    //INSTANCE VARIABLES
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String username;
    @NotNull
    private String password;
    private String name;
    private String email_address;
    private String home_address;

    //CONSTRUCTORS
    public Client(){

    };

    public Client(
            @NotNull String username,
            @NotNull String password,
            String name,
            String email_address,
            String home_address
    ) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email_address = email_address;
        this.home_address = home_address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //SETTERS AND GETTERS
    @NotNull
    public String getUsername() {
        return username;
    }

    public void setUsername(@NotNull String username) {
        this.username = username;
    }

    @NotNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NotNull String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getHome_address() {
        return home_address;
    }

    public void setHome_address(String home_address) {
        this.home_address = home_address;
    }

    public boolean validate(){
        if (this.username == null || this.username.trim().isEmpty()) {
            return false;
        }
        if (this.password == null || this.password.trim().isEmpty()) {
            return false;
        }
        return true;
    }
}
