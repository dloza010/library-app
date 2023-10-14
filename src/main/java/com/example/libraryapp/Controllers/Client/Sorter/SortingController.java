package com.example.libraryapp.Controllers.Client.Sorter;
import com.example.libraryapp.entity.Book.Book;
import com.example.libraryapp.repositories.BookRepository;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/book")

public class SortingController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/sort-title") 
    public List<Book> sortByTitle() {
        List<Book> books = bookRepository.findAll();
        ArrayList<Book> books2 = new ArrayList<>(books);
        Collections.sort(books2, (book1, book2) -> book1.getTitle().compareTo(book2.getTitle()));

        for(Book a:books2)
			System.out.println(a.getTitle());
        return books2;
    }    
}