package com.example.libraryapp.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.libraryapp.entity.Book.Book;
import com.example.libraryapp.entity.Client.Client;
import com.example.libraryapp.entity.WishList.WishList;
import com.example.libraryapp.repositories.BookRepository;
import com.example.libraryapp.repositories.ClientRepository;
import com.example.libraryapp.repositories.WishListRepository;

import java.util.List;

@RestController
@RequestMapping("/wish-list")
public class WishListController {

    private final ClientRepository clientRepository;
    private final BookRepository bookRepository;
    private final WishListRepository wishListRepository;

    public WishListController(ClientRepository clientRepository, BookRepository bookRepository, WishListRepository wishListRepository) {
        this.clientRepository = clientRepository;
        this.bookRepository = bookRepository;
        this.wishListRepository = wishListRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createWishList(@RequestParam Long clientId, @RequestParam String wishListName) {
        Client client = clientRepository.findById(clientId).orElse(null);

        if (client == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        WishList existingWishList = wishListRepository.findByUserAndName(client, wishListName);
        if (existingWishList != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
        }

        WishList wishList = new WishList(client, wishListName);
        wishListRepository.save(wishList);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/add-book")
    public ResponseEntity<Void> addBookToWishList(@RequestParam Long wishListId, @RequestParam Long bookId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        WishList wishList = wishListRepository.findById(wishListId).orElse(null);

        if (book == null || wishList == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        wishList.addBook(book);
        wishListRepository.save(wishList);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/remove-book")
    public ResponseEntity<Void> removeBookFromWishList(@RequestParam Long wishListId, @RequestParam Long bookId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        WishList wishList = wishListRepository.findById(wishListId).orElse(null);

        if (book == null || wishList == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        wishList.removeBook(book);
        wishListRepository.save(wishList);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/list-books")
    public ResponseEntity<List<Book>> listBooksInWishList(@RequestParam Long wishListId) {
        WishList wishList = wishListRepository.findById(wishListId).orElse(null);

        if (wishList == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Book> booksInWishList = wishList.getBooks();

        return new ResponseEntity<>(booksInWishList, HttpStatus.OK);
    }
}
