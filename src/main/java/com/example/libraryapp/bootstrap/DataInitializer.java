package com.example.libraryapp.bootstrap;

import com.example.libraryapp.entity.Client;
import com.example.libraryapp.repositories.LibraryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final LibraryRepository libraryRepository;

    public DataInitializer(LibraryRepository libraryRepository){
        this.libraryRepository = libraryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Client clientDDD = new Client( "david", "dlz", "password");

        System.out.println("Id: " + clientDDD.getId());

        Client savedDDD = libraryRepository.save(clientDDD);

        System.out.println("Id: " + savedDDD.getId());

        Client clientSIA = new Client("lucas", "dlz", "password1");
        Client savedSIA = libraryRepository.save(clientSIA);

        libraryRepository.findAll().forEach(client -> {
            System.out.println("User first name: " + client.getName());
            System.out.println("Username: " +  client.getUsername());
        });
    }
}



