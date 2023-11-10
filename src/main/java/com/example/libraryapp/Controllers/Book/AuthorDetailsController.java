package com.example.libraryapp.Controllers.Book;

import com.example.libraryapp.JSON_Mapping.Create_Author;
import com.example.libraryapp.JSON_Mapping.Create_Books;
import com.example.libraryapp.Services.Book.Author_Details_Service;
import com.example.libraryapp.Services.Book.Book_Details_Service;
import com.example.libraryapp.entity.Book.Book_Details;
import com.example.libraryapp.entity.Client.Client;
import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path ="/author", method = RequestMethod.GET)
public class AuthorDetailsController {
    private final Book_Details_Service bookDetailsService;
    private final Author_Details_Service authorDetailsService;

    //instantiate a author details controller instance
    //and autowire dependencies (i.e. book details service, author details service)
    @Autowired
    public AuthorDetailsController(Book_Details_Service bookDetailsService, Author_Details_Service authorDetailsService) {
        this.bookDetailsService = bookDetailsService;
        this.authorDetailsService = authorDetailsService;
    }

    //allow admin to add author details to db
    @PostMapping("/{user_id}/create")
    public ResponseEntity<List> createBook(@PathVariable("user_id") long user_id,
                                           @RequestBody Create_Author create_author){

        //check that user is admin. if not admin, then reject request
        Client client = bookDetailsService.isAdmin(user_id);
        if(client != null){
            boolean query = authorDetailsService.createAuthor(create_author.getAuthorFirstName(), create_author.getAuthorLastName(),
                    create_author.getBiography(), create_author.getPublisher_name());
            if (query == false) {
                return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
            }
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
