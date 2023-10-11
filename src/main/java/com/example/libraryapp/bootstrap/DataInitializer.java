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
    private final List<Client> clientList = new ArrayList<>();
    private final List<Book> bookList = new ArrayList<>();
    public DataInitializer(ClientRepository clientRepository, BookRepository bookRepository){
        this.clientRepository = clientRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        clientSeeder(3, this.clientList, this.clientRepository);
        bookSeeder(5, this.bookList, this.bookRepository, 1L);
        bookSeeder(5, this.bookList, this.bookRepository, 2L);
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

    private void bookSeeder(
            int numOfBooks,
            List<Book> bookList,
            BookRepository bookRepository,
            Long clientId
    ){

        Faker faker = new Faker();

        for (int i = 0; i < numOfBooks; i++){
            String title = faker.book().title();
            int quantity = faker.number().numberBetween(1, 20);
            int releaseYear = faker.number().numberBetween(1990, 2010);
            String author = faker.book().author();
            Client client = clientRepository.findById(clientId).orElseThrow(
                    () -> new IllegalArgumentException("Client not found with ID: " + clientId)
            );

            Book book = new Book(title, quantity, releaseYear, author, client);
            bookList.add(book);
        }

        bookRepository.saveAll(bookList);

    }
}



