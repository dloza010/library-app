package com.example.libraryapp.Services.Book;

import com.example.libraryapp.entity.Book.Book_Details;
import com.example.libraryapp.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Book_Details_Service {
    private final BookRepository bookRepository;
    private final Book_Author_Intermediate_Repository bookAuthorIntermediateRepository;
    private final PublisherRepository publisherRepository;
    private final Author_Details_Repository authorDetailsRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public Book_Details_Service(BookRepository bookRepository, Book_Author_Intermediate_Repository bookAuthorIntermediateRepository,
        PublisherRepository publisherRepository, Author_Details_Repository authorDetailsRepository, ClientRepository clientRepository) {
        this.bookRepository = bookRepository;
        this.bookAuthorIntermediateRepository = bookAuthorIntermediateRepository;
        this.publisherRepository = publisherRepository;
        this.authorDetailsRepository = authorDetailsRepository;
        this.clientRepository = clientRepository;
    }

    public Book_Details getBookByID(long isbn){
        Book_Details bookDetails = bookRepository.findById(isbn).orElse(null);
        return bookDetails;
    }
}
