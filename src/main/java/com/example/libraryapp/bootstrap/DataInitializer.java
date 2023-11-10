package com.example.libraryapp.bootstrap;

import com.example.libraryapp.entity.Book.Author_Details;
import com.example.libraryapp.entity.Book.Book_Author_Intermediate;
import com.example.libraryapp.entity.Book.Book_Details;
import com.example.libraryapp.entity.Book.Publisher;
import com.example.libraryapp.entity.Cart.Cart_Item;
import com.example.libraryapp.entity.Cart.Shopping_Cart;
import com.example.libraryapp.entity.Client.Books_Owned;
import com.example.libraryapp.entity.Client.Client;
import com.example.libraryapp.entity.Comments.Comments;
import com.example.libraryapp.entity.Comments.Ratings;
import com.example.libraryapp.entity.WishList.WishList;
import com.example.libraryapp.repositories.*;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Long.parseLong;


@Component
public class DataInitializer implements CommandLineRunner {

    private final ClientRepository clientRepository;
    private final Book_Details_Repository bookRepository;
    private final PublisherRepository publisherRepository;
    private final Book_Author_Intermediate_Repository bookAuthorIntermediateRepository;
    private final Author_Details_Repository authorDetailsRepository;
    private List<Client> clientList = new ArrayList<>();
    private List<Book_Details> bookList = new ArrayList<>();
    private List<Publisher> publisherList = new ArrayList<>();
    private List<Book_Author_Intermediate> bookAuthorIntermediates = new ArrayList<>();
    private List<Author_Details> bookAuthorDetails = new ArrayList<>();
    private List<Cart_Item> cartItemsList;
    private Cart_Item_Repository cartItemRepository;
    private List<Shopping_Cart> shoppingCartsList;
    private Shopping_Cart_Repository shoppingCartRepository;
    private Books_Owned_Repository booksOwnedRepository;
    private List<Comments> commentsList;
    private Comments_Repository commentsRepository;
    private List<Ratings> ratingsList;
    private Ratings_Repository ratingsRepository;
    private List<Books_Owned> booksOwnedList;
    private List<WishList> wishList;
    private Wishlist_Repository wishlistRepository;

    public DataInitializer(ClientRepository clientRepository, Book_Details_Repository bookRepository, PublisherRepository publisherRepository,
                           Book_Author_Intermediate_Repository bookAuthorIntermediateRepository, Author_Details_Repository authorDetailsRepository,
                           Shopping_Cart_Repository shoppingCartRepository, Cart_Item_Repository cartItemRepository,
                           Books_Owned_Repository booksOwnedRepository, Comments_Repository commentsRepository, Ratings_Repository ratingsRepository,
                           Wishlist_Repository wishlistRepository){
        this.clientRepository = clientRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
        this.bookAuthorIntermediateRepository = bookAuthorIntermediateRepository;
        this.authorDetailsRepository = authorDetailsRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.cartItemRepository = cartItemRepository;
        this.booksOwnedRepository = booksOwnedRepository;
        this.commentsRepository = commentsRepository;
        this.ratingsRepository = ratingsRepository;
        this.wishlistRepository = wishlistRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        clientSeeder(8, this.clientList, this.clientRepository);
        bookSeeder(30, this.bookList, this.bookRepository, this.publisherList, this.publisherRepository,
                this.bookAuthorIntermediates, this.bookAuthorIntermediateRepository, this.bookAuthorDetails, this.authorDetailsRepository);
        shoppingCartSeeder(this.clientRepository,this.bookRepository,this.cartItemsList,this.cartItemRepository, this.shoppingCartsList,this.shoppingCartRepository);

        //the below seeders are not yet implemented
        booksOwnedSeeder(this.clientRepository,this.bookRepository,this.commentsRepository,this.ratingsRepository,this.booksOwnedList,this.booksOwnedRepository);
        commentsSeeder(2,this.clientRepository,this.bookRepository,this.booksOwnedRepository,this.commentsList,this.commentsRepository,this.ratingsList,this.ratingsRepository);
        wishlistSeeder(3,this.clientRepository,this.bookRepository,this.wishList,this.wishlistRepository);
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
            boolean isAdmin = faker.bool().bool();

            Client client = new Client(username, password, name, email, address, isAdmin);
            clientList.add(client);
        }

        clientRepository.saveAll(clientList);
    }

    private static void bookSeeder(
            int numOfBooks,
            List<Book_Details> bookList,
            Book_Details_Repository bookRepository,
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
        String biography = faker.lorem().paragraphs(3).toString();
        for (int i = 0; i < numOfBooks; i++){
            long ISBN = parseLong(faker.code().isbn13());
            int copies_sold = faker.number().numberBetween(1, 20);
            int year_published = faker.number().numberBetween(1990, 2010);
            //String first_name = String.valueOf(faker.name().firstName());
            //String last_name = String.valueOf(faker.name().lastName());
            String description = "N/A";
            double price = Double.parseDouble(faker.commerce().price(0,50));
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
                biography = faker.lorem().paragraphs(3).toString();
            }
            Author_Details authorDetails = new Author_Details(authorFirstName, authorLastName,biography);
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

    private static void wishlistSeeder(
            int numOfWishes,
            ClientRepository clientRepository,
            Book_Details_Repository bookRepository,
            List<WishList> wishlists,
            Wishlist_Repository wishlist
    ){
        Faker faker = new Faker();

        for (long i = 0; i < clientRepository.count(); i++){
            Client client = clientRepository.getReferenceById(i+1);



        }

        //clientRepository.saveAll(clientList);
    }

    private static void commentsSeeder(
            //number of comments for each person
            int numOfComments,
            ClientRepository clientRepository,
            Book_Details_Repository bookRepository,
            Books_Owned_Repository booksOwnedRepository,
            List<Comments> comments,
            Comments_Repository commentsRepository,
            List<Ratings> ratings,
            Ratings_Repository ratingsRepository
    ){
        Faker faker = new Faker();

        for (int i = 0; i < clientRepository.count(); i++){



        }

        //clientRepository.saveAll(clientList);
    }

    private static void shoppingCartSeeder(
            ClientRepository clientRepository,
            Book_Details_Repository bookRepository,
            List<Cart_Item> cart_items,
            Cart_Item_Repository cartItemRepository,
            List<Shopping_Cart> shopping_carts,
            Shopping_Cart_Repository shoppingCartRepository
    ){
        Faker faker = new Faker();
        List<Book_Details> books = bookRepository.findAll();
        List<Client> client = clientRepository.findAll();
        shopping_carts = new ArrayList<Shopping_Cart>();
        cart_items = new ArrayList<Cart_Item>();
        for (int i = 0; i < (int) client.size(); i++){
            //System.out.println(client);
            //int randomNum = (int)(Math.random() * 3) + 1;
            Shopping_Cart shopping_cart = new Shopping_Cart(client.get(i));
            shopping_carts.add(shopping_cart);

            //adding random number of cart items to shopping carts for each client/user
            for(int n = 0; n < 5; n++){
                int randomBookIndex = (int)(Math.random() * (int) bookRepository.count());
                int randomQuantity = (int)(Math.random() * 3) + 1;
                Cart_Item cart_item = new Cart_Item(shopping_cart, books.get(randomBookIndex), randomQuantity);
                cart_items.add(cart_item);
            }
        }
        shoppingCartRepository.saveAll(shopping_carts);
        cartItemRepository.saveAll(cart_items);
    }
    
    private static void booksOwnedSeeder(
            ClientRepository clientRepository,
            Book_Details_Repository bookRepository,
            Comments_Repository commentsRepository,
            Ratings_Repository ratingsRepository,
            List<Books_Owned> booksOwned,
            Books_Owned_Repository booksOwnedRepository
    ){
        Faker faker = new Faker();

        for (int i = 0; i < clientRepository.count(); i++){
            

            
        }

        //clientRepository.saveAll(clientList);
    }
}



