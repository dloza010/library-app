package com.example.libraryapp.Controllers.Book;

import com.example.libraryapp.JSON_Mapping.Create_Books;
import com.example.libraryapp.Services.Book.Book_Details_Service;
import com.example.libraryapp.entity.Book.Book_Details;
import com.example.libraryapp.entity.Client.Client;
import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path ="/books", method = RequestMethod.GET)
public class BookDetailsController {

    private final Book_Details_Service bookDetailsService;

    @Autowired
    public BookDetailsController(Book_Details_Service bookDetailsService) {
        this.bookDetailsService = bookDetailsService;
    }
//    @GetMapping("")
//    public List<Object[]> getAllBookDetails() {
//        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-h2-return-multiple-entities");
//        EntityManager entityManager = emf.createEntityManager();
//        Query query = entityManager.createQuery("select " +
//                "Book_Details.ISBN,Book_Details.description,Book_Details.price,Book_Details.genre," +
//                "Book_Details.year_published,Book_Details.copies_sold," +
//                "Publisher.publisher_name,Author_Details.first_name,Author_Details.last_name " +
//                "from Book_Details inner join Publisher on Book_Details.publisher_id = Publisher.publisher_id " +
//                "inner join Book_Author_Intermediate on Book_Details.ISBN = Book_Author_Intermediate.ISBN " +
//                "inner join Author_Details on Book_Author_Intermediate.author_id = Author_Details.author_id");
//
//        return query.getResultList();
//    }

    //Retrieve a book's details by the ISBN
    @GetMapping("/{ISBN}")
    public ResponseEntity<Book_Details> getBookById(@PathVariable("ISBN") long isbn) {
        Book_Details bookDetails = bookDetailsService.getBookByID(isbn);

        if (bookDetails == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(bookDetails, HttpStatus.OK);
    }

    //allow admin to add book details to db
    @PostMapping("/{user_id}/create")
    public ResponseEntity<List> createBook(@PathVariable("user_id") long user_id,
                                           @RequestBody Create_Books create_books){

        //check that user is admin. if not admin, then reject request
        Client client = bookDetailsService.isAdmin(user_id);
        if(client != null){
            boolean query = bookDetailsService.createBook(create_books.getISBN(), create_books.getTitle(),
                    create_books.getDescription(), create_books.getPrice(), create_books.getGenre(), create_books.getYear_published(),
                    create_books.getCopies_sold(), create_books.getPublisher_name(), create_books.getAuthorFirstName(),
                    create_books.getAuthorLastName());
            if (query == false) {
                return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
            }
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
