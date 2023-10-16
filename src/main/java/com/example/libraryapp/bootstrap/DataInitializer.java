package com.example.libraryapp.bootstrap;

import com.example.libraryapp.entity.Book.Book;
import com.example.libraryapp.entity.Client.Client;
//import com.example.libraryapp.entity.WishList.WishList;
import com.example.libraryapp.repositories.BookRepository;
import com.example.libraryapp.repositories.ClientRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
//import com.example.libraryapp.repositories.WishListRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    //private static WishListRepository wishListRepository;
    private static ClientRepository clientRepository;
    private static BookRepository bookRepository;
    private List<Client> clientList = new ArrayList<>();
    private List<Book> bookList = new ArrayList<>();
    public DataInitializer(){
      //  this.clientRepository = clientRepository;
      //  this.bookRepository = bookRepository;
        //this.wishListRepository = wishListRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        clientSeeder(3, this.clientList, DataInitializer.clientRepository);
        bookSeeder(10, this.bookList, DataInitializer.bookRepository);
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
            String emailAddress = faker.internet().emailAddress();
            String homeAddress = faker.address().streetAddress();

            Client client = new Client(name, username, password, emailAddress, homeAddress);
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
            int price = faker.number().numberBetween(20, 100);
            String genre = faker.book().genre();

            Book book = new Book(title, quantity, releaseYear, author, price, genre);
            bookList.add(book);
        }

        bookRepository.saveAll(bookList);

    }
    /*public static WishList wishListInitializer(Long clientid){

        WishList wishList = new WishList(clientid);
        return wishListRepository.save(wishList);
    }*/
}



