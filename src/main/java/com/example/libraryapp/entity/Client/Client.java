package com.example.libraryapp.entity.Client;

import com.example.libraryapp.repositories.ClientRepository;
import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Objects;


@Entity
public class Client implements Serializable {

    //INSTANCE VARIABLES
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String username;
    @NotNull
    private String password;
    private String name;
    private String emailAddress;
    private String homeAddress;
    private boolean isAdmin;

    //CONSTRUCTORS
    public Client(){

    };

    public Client(
            @NotNull String username,
            @NotNull String password,
            String name,
            String emailAddress,
            String homeAddress,
            boolean isAdmin
    ) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.emailAddress = emailAddress;
        this.homeAddress = homeAddress;
        this.isAdmin = isAdmin;
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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
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

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
