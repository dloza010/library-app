package com.example.libraryapp.bootstrap;

import com.example.libraryapp.entity.Book.Book;
import com.example.libraryapp.entity.Client.Client;
import com.example.libraryapp.repositories.BookRepository;
import com.example.libraryapp.repositories.ClientRepository;
import com.github.javafaker.Faker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;


@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private BookRepository bookRepository;
    private List<Client> clientList = new ArrayList<>();
    private List<Book> bookList = new ArrayList<>();
    
    @Override
    public void run(String... args) throws Exception {

<<<<<<< HEAD
        clientSeeder(3, this.clientList, this.clientRepository);
        bookSeeder(20, this.bookList, this.bookRepository);
=======
        clientSeeder(3, this.clientList, clientRepository);
        bookSeeder(10, this.bookList, bookRepository);
>>>>>>> main
    }

    private void clientSeeder(
            int numOfClients,
            List<Client> clientList,
            ClientRepository clientRepository
    ){

        Faker faker = new Faker();

        for (int i = 0; i < numOfClients; i++){
            String  username = faker.name().username();
            String password = faker.internet().password();
            String name = faker.name().firstName();
            String email = faker.internet().emailAddress();
            String address = faker.address().streetAddress();

            Client client = new Client(username, password, name, email, address);
            clientList.add(client);
        }

        clientRepository.saveAll(clientList);

    }

    private static void bookSeeder(
            int numOfBooks,
            List<Book> bookList,
            BookRepository bookRepository
    ){

        Faker faker = new Faker();

        for (int i = 0; i < numOfBooks; i++){
            String title = faker.book().title();
            int quantity = faker.number().numberBetween(1, 20);
            int releaseYear = faker.number().numberBetween(1990, 2010);
            String author = faker.book().author();
<<<<<<< HEAD
            String genre = faker.book().genre();

            Book book = new Book(title, quantity, releaseYear, author, genre);
=======
            int price = faker.number().numberBetween(20, 100);
            String genre = faker.book().genre();

            Book book = new Book(title, quantity, releaseYear, author, price, genre);
>>>>>>> main
            bookList.add(book);
        }

        bookRepository.saveAll(bookList);

    }
}



