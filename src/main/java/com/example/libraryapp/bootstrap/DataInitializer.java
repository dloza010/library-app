package com.example.libraryapp.bootstrap;

import com.example.libraryapp.entity.Book.Author_Details;
import com.example.libraryapp.entity.Book.Book_Author_Intermediate;
import com.example.libraryapp.entity.Book.Book_Details;
import com.example.libraryapp.entity.Book.Publisher;
import com.example.libraryapp.entity.Client.Client;
import com.example.libraryapp.repositories.*;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Long.parseLong;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ClientRepository clientRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    private final Book_Author_Intermediate_Repository bookAuthorIntermediateRepository;
    private final Author_Details_Repository authorDetailsRepository;
    private List<Client> clientList = new ArrayList<>();
    private List<Book_Details> bookList = new ArrayList<>();
    private List<Publisher> publisherList = new ArrayList<>();
    private List<Book_Author_Intermediate> bookAuthorIntermediates = new ArrayList<>();
    private List<Author_Details> bookAuthorDetails = new ArrayList<>();
    public DataInitializer(ClientRepository clientRepository, BookRepository bookRepository, PublisherRepository publisherRepository,
                           Book_Author_Intermediate_Repository bookAuthorIntermediateRepository, Author_Details_Repository authorDetailsRepository){
        this.clientRepository = clientRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
        this.bookAuthorIntermediateRepository = bookAuthorIntermediateRepository;
        this.authorDetailsRepository = authorDetailsRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        clientSeeder(8, this.clientList, this.clientRepository);
        bookSeeder(30, this.bookList, this.bookRepository, this.publisherList, this.publisherRepository,
                this.bookAuthorIntermediates, this.bookAuthorIntermediateRepository, this.bookAuthorDetails, this.authorDetailsRepository);
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
            boolean isAdmin = faker.bool().bool();

            Client client = new Client(name, username, password, isAdmin);
            clientList.add(client);
        }

        clientRepository.saveAll(clientList);

    }

    private static void bookSeeder(
            int numOfBooks,
            List<Book_Details> bookList,
            BookRepository bookRepository,
            List<Publisher> publisherList,
            PublisherRepository publisherRepository,
            List<Book_Author_Intermediate> bookAuthorIntermediates,
            Book_Author_Intermediate_Repository bookAuthorIntermediateRepository,
            List<Author_Details> bookAuthorDetails,
            Author_Details_Repository authorDetailsRepository
    ){

        Faker faker = new Faker();
        String publisher_name = faker.book().publisher();
        //to create random number to generate authors randomly
        //Random rand = new Random();
        //int rand_int = 0;
        String authorFirstName = faker.name().firstName();
        String authorLastName = faker.name().lastName();
        for (int i = 0; i < numOfBooks; i++){
            long ISBN = parseLong(faker.code().isbn13());
            int copies_sold = faker.number().numberBetween(1, 20);
            int year_published = faker.number().numberBetween(1990, 2010);
            //String first_name = String.valueOf(faker.name().firstName());
            //String last_name = String.valueOf(faker.name().lastName());
            String description = "N/A";
            double price = faker.number().randomDouble(2,0,1999999999);
            String genre = faker.book().genre();
            String title = faker.book().title();
            //create a publisher name and add to db
            //duplicate publisher name 3x then add a new publisher per each new book
            if(i % 3 == 0){
                publisher_name = faker.book().publisher();
            }
            Publisher publisher = new Publisher(publisher_name);
            publisherList.add(publisher);
            //create a new author and add to db
            //duplicate author details 2x then add a new author per each new book
            //rand_int = rand.nextInt(numOfBooks + 1);
            if(i % 2 == 0){
                authorFirstName = faker.name().firstName();
                authorLastName = faker.name().lastName();
            }
            Author_Details authorDetails = new Author_Details(authorFirstName, authorLastName);
            bookAuthorDetails.add(authorDetails);
            Book_Details book = new Book_Details(ISBN, title, description, price, genre, year_published, copies_sold, publisher);

            bookList.add(book);
            //add record/entry to Book_Author_Intermediates Table
            Book_Author_Intermediate bookAuthorIntermediate = new Book_Author_Intermediate(authorDetails, book);
            bookAuthorIntermediates.add(bookAuthorIntermediate);
        }

        publisherRepository.saveAll(publisherList);
        authorDetailsRepository.saveAll(bookAuthorDetails);
        bookRepository.saveAll(bookList);
        bookAuthorIntermediateRepository.saveAll(bookAuthorIntermediates);






    }
}



