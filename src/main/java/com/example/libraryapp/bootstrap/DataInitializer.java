package com.example.libraryapp.bootstrap;

import com.example.libraryapp.entity.Book.Book;
import com.example.libraryapp.entity.Client.Client;
import com.example.libraryapp.repositories.BookRepository;
import com.example.libraryapp.repositories.ClientRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ClientRepository clientRepository;
    private final BookRepository bookRepository;
    private List<Client> clientList = new ArrayList<>();
    private List<Book> bookList = new ArrayList<>();
    public DataInitializer(ClientRepository clientRepository, BookRepository bookRepository){
        this.clientRepository = clientRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        clientSeeder(3, this.clientList, this.clientRepository);
        bookSeeder(10, this.bookList, this.bookRepository);
    }

    private static void clientSeeder(
            int numOfClients,
            List<Client> clientList,
            ClientRepository clientRepository
    ){

        Faker faker = new Faker();

        for (int i = 0; i < numOfClients; i++){
            String name = faker.name().firstName();
            String  username = faker.name().username();
            String password = faker.internet().password();

            Client client = new Client(name, username, password);
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
            String genre = faker.book().genre();

            Book book = new Book(title, quantity, releaseYear, author, genre);
            bookList.add(book);
        }

        bookRepository.saveAll(bookList);

    }
}



