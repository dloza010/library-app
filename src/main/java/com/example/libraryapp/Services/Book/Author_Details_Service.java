package com.example.libraryapp.Services.Book;

import com.example.libraryapp.entity.Book.Author_Details;
import com.example.libraryapp.entity.Book.Book_Author_Intermediate;
import com.example.libraryapp.entity.Book.Book_Details;
import com.example.libraryapp.entity.Book.Publisher;
import com.example.libraryapp.entity.Client.Client;
import com.example.libraryapp.Services.Book.Book_Details_Service;
import com.example.libraryapp.repositories.*;
import com.example.libraryapp.repositories.PublisherRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

//Author Details Service
@Service
public class Author_Details_Service {
    private List<Author_Details> bookAuthorDetails = new ArrayList<>();
    private final Author_Details_Repository authorDetailsRepository;
    private final PublisherRepository publisherRepository;
    private List<Publisher> publisherList = new ArrayList<>();
    //instantiate author details service
    //autowire author details repository dependency
    @Autowired
    public Author_Details_Service(Author_Details_Repository authorDetailsRepository, PublisherRepository publisherRepository){
        this.authorDetailsRepository = authorDetailsRepository;
        this.publisherRepository = publisherRepository;
    }

    //create an author
    //@params: authorFirstName, authorLastName, biography, publisher
    //Note 1: future implementation: helper function/logic to check if publisher already exists and if so add link to new author
    //Note 2: future implementation: helper function/logic to check if author already exists and if so add link to existing publisher (if not linked and
    // if linked return "yes" as author exists along with publisher)
    // or create new publisher and link to existing author
    public boolean createAuthor(String authorFirstName, String authorLastName, String biography, String publisher_name){
        boolean method_success_flag = true;
        try{
            Publisher publisher = new Publisher(publisher_name);
            Author_Details author = new Author_Details(authorFirstName, authorLastName, biography);

            //add to staging lists of records for each entity which will be committed to db
            publisherList.add(publisher);
            bookAuthorDetails.add(author);

            //commit new data to db
            publisherRepository.saveAll(publisherList);
            authorDetailsRepository.saveAll(bookAuthorDetails);

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
