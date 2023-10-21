package com.example.libraryapp.Controllers;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.libraryapp.repositories.ClientRepository;
import com.example.libraryapp.repositories.WishListRepository;
import com.example.libraryapp.entity.Book.Book;
import com.example.libraryapp.entity.Client.Client;
import com.example.libraryapp.entity.WishList.WishList;
//import com.example.libraryapp.entity.WishListItem.WishListItem;
import com.example.libraryapp.repositories.BookRepository;


@RestController
@RequestMapping("/wish-list")
public class WishListController {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private WishListRepository wishListRepository;

    @PostMapping("/user/{clientid}/book/{bookid}")
    public ResponseEntity<WishList> getBookById(@PathVariable("clientid") Long clientid, @PathVariable("bookid") Long bookid) {
        Client client = clientRepository.findById(clientid).orElse(null);
        Book book = bookRepository.findById(bookid).orElse(null);

        if (client == null || book == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        WishList wishList = new WishList(client);
        wishListRepository.save(wishList);

        return new ResponseEntity<>(wishList, HttpStatus.OK);
    }

}
