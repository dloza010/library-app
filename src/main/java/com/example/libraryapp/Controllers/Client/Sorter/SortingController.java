package com.example.libraryapp.Controllers.Client.Sorter;
import com.example.libraryapp.entity.Book.Book;
import com.example.libraryapp.repositories.BookRepository;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

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
   
    @GetMapping("/sort-top10")
    public List<Book> sortTop10() {
        
        List<Book> books = bookRepository.findAll();
        ArrayList<Book> books2 = new ArrayList<>(books);
        System.out.println(books);
        
        class QuantityComparator implements Comparator<Book> {
        @Override
        public int compare(Book a, Book b) {
            return b.getQuantity() - a.getQuantity();
            }
        }
        Collections.sort(books2, new QuantityComparator());
        //this just shows the sorted quantities in the terminal
        for(Book book:books2) {
            System.out.println(book.getQuantity());
        } 

        List<Book> top10Books = books2.subList(0, Math.min(10, books2.size()));

        return top10Books;
    }
}