package com.example.libraryapp.Services.Book;

import com.example.libraryapp.entity.Book.Author_Details;
import com.example.libraryapp.entity.Book.Book_Author_Intermediate;
import com.example.libraryapp.entity.Book.Book_Details;
import com.example.libraryapp.entity.Book.Publisher;
import com.example.libraryapp.entity.Client.Client;
import com.example.libraryapp.repositories.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class Book_Details_Service {
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

    private final Book_Details_Repository bookDetailsRepository;

    @Autowired
    public Book_Details_Service(BookRepository bookRepository, Book_Author_Intermediate_Repository bookAuthorIntermediateRepository,
                                PublisherRepository publisherRepository, Author_Details_Repository authorDetailsRepository,
                                ClientRepository clientRepository, Book_Details_Repository bookDetailsRepository) {
        this.bookRepository = bookRepository;
        this.bookAuthorIntermediateRepository = bookAuthorIntermediateRepository;
        this.publisherRepository = publisherRepository;
        this.authorDetailsRepository = authorDetailsRepository;
        this.clientRepository = clientRepository;
        this.bookDetailsRepository = bookDetailsRepository;
    }

    //private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BooksStore");

    //find all book details from book details entity along with author and publisher
    //to be implemented
//    public List<Object[]> findBookById(long ISBN){
//
//        return ;
//    }

    //return client data for an admin
    public List<Client> findAdminById(long id){
        List admin = clientRepository.findAdminById(id);
        return admin;
    }

    //return all book details by ISBN
    //--Exceptions with below method; unsure on how to resolve
    public List findBookDetailsById(long ISBN){
        List author_id = bookAuthorIntermediateRepository.findAuthorId(ISBN);
        List publisher_id = publisherRepository.findPublisherId(ISBN);
        long publisherId;
        long authorId;
        if(publisher_id == null){
            publisherId = 0;
        }else{
            //Object publisherId1 = publisher_id.get(0)[0].get(0);
            //publisherId = Long.parseLong(publisher_id.getElement().toString());
            //publisherId = Long.parseLong(publisher_id.get(0)[0].toString());
            publisherId = ((Long)publisher_id.get(0));
        }
        if(author_id == null){
            authorId = 0;
        }else{
            //authorId = Long.parseLong(author_id.getElement().toString());
            authorId = ((Long)author_id.get(0));
        }
        return bookDetailsRepository.findFullBookDetails(ISBN, authorId, publisherId);
    }

    public Book_Details getBookByID(long isbn){
        Book_Details bookDetails = bookRepository.findById(isbn).orElse(null);
        return bookDetails;
    }

    //check if user is admin
    public Client isAdmin(long user_id){
        Client user = clientRepository.findById(user_id).orElse(null);
        if(user.isAdmin() == true){
            return user;
        }else{
            return null;
        }
    }

    //create a book with ISBN, book
    //name, book description, price, author, genre,
    // publisher , year published and
    //copies sold
    public boolean createBook(long ISBN, String title, String description, double price, String genre, int year_published,
                                                 int copies_sold, String publisher_name, String authorFirstName, String authorLastName){
        boolean method_success_flag = true;
        try{
            Publisher publisher = new Publisher(publisher_name);
            Book_Details book_details = new Book_Details(ISBN, title, description, price, genre, year_published, copies_sold, publisher);
            Author_Details author = new Author_Details(authorFirstName, authorLastName);
            Book_Author_Intermediate book_author_intermediate = new Book_Author_Intermediate(author, book_details);

            //add to staging lists of records for each entity which will be committed to db
            publisherList.add(publisher);
            bookList.add(book_details);
            bookAuthorDetails.add(author);
            bookAuthorIntermediates.add(book_author_intermediate);

            //commit new data to db
            publisherRepository.saveAll(publisherList);
            bookRepository.saveAll(bookList);
            authorDetailsRepository.saveAll(bookAuthorDetails);
            bookAuthorIntermediateRepository.saveAll(bookAuthorIntermediates);

            //retrieve book details
            //findBList bookDetails;
            //bookDetails = findBookDetailsById(ISBN);

            //update JSON object result if more than 1 authors to include author names
            // and exclude duplicate query result rows for other book details
            //to be implemented later
//        List bookDetailsRevised;
//        if(bookDetails.size() > 0){
//            bookDetailsRevised = bookDetails.
//        }
        }catch(Exception e){
            method_success_flag = false;
        }
        return method_success_flag;
    }
}
