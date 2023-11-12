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

import java.lang.reflect.Array;
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
    private final Book_Author_Intermediate_Repository bookAuthorIntermediateRepository;
    private List<Book_Author_Intermediate> bookAuthorIntermediates = new ArrayList<>();
    private final Book_Details_Repository bookDetailsRepository;
    //instantiate author details service
    //autowire author details repository dependency
    @Autowired
    public Author_Details_Service(Author_Details_Repository authorDetailsRepository, PublisherRepository publisherRepository,
                                  Book_Author_Intermediate_Repository bookAuthorIntermediateRepository, Book_Details_Repository bookDetailsRepository){
        this.authorDetailsRepository = authorDetailsRepository;
        this.publisherRepository = publisherRepository;
        this.bookAuthorIntermediateRepository = bookAuthorIntermediateRepository;
        this.bookDetailsRepository = bookDetailsRepository;
    }
    //return all book details by Author id
    //Note 1: for future: implement case when author does not exist
    public List findBookDetailsByAuthorId(long id){
        List bookISBN = bookAuthorIntermediateRepository.findBookISBN(id);
        ArrayList<Long> bookISBNs = new ArrayList<Long>();
        ArrayList<Long> publisher_id = new ArrayList<Long>();
        List book_details = new ArrayList<Long>();
        for (int i = 0; i < bookISBN.size(); i++)
        {
            //Long z = (Long) bookISBN.get(i);
            bookISBNs.add((Long) bookISBN.get(i));
        }
        for (int i = 0; i < bookISBNs.size(); i++)
        {
            publisher_id.add((Long) publisherRepository.findPublisherId(bookISBNs.get(i)).get(0));
        }
        for (int i = 0; i < bookISBN.size(); i++)
        {
            book_details.add(bookDetailsRepository.findFullBookDetails((Long) bookISBNs.get(i), id, (Long) publisher_id.get(i)));
        }

        return book_details;
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

//        }
        }catch(Exception e){
            method_success_flag = false;
        }
        return method_success_flag;
    }
}
