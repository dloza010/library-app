package com.example.libraryapp.Controllers.Rating;

import com.example.libraryapp.entity.Book.Book_Details;
import com.example.libraryapp.entity.Client.Client;
import com.example.libraryapp.repositories.Book_Details_Repository;
import com.example.libraryapp.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.libraryapp.entity.Comments.Ratings;
import com.example.libraryapp.entity.Comments.Comments;
import com.example.libraryapp.repositories.Comments_Repository;
import com.example.libraryapp.repositories.Ratings_Repository;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private Comments_Repository commentsRepository;

    @Autowired
    private Ratings_Repository ratingsRepository;

    @Autowired
    private Book_Details_Repository bookDetailsRepository;

    @PostMapping("/rate")
    public ResponseEntity<?> rateBook(@RequestBody Ratings rating) {
        Ratings savedRating = ratingsRepository.save(rating);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/comment")
    public ResponseEntity<?> commentBook(@RequestBody Comments comments) {
        Comments savedComment = commentsRepository.save(comments);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/comments/{bookId}")
    public ResponseEntity<?> getComments(@PathVariable long bookId) {
        Book_Details book = bookDetailsRepository.findById(bookId).orElse(null);

        if (book == null) {
            return ResponseEntity.badRequest().body("Book not found.");
        }
        List<String> comments = commentsRepository.findByBookId(bookId);

        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/averageRating/{bookId}")
    public ResponseEntity<?> getAverageRating(@PathVariable long bookId) {
        Book_Details book = bookDetailsRepository.findById(bookId).orElse(null);

        if (book == null) {
            return ResponseEntity.badRequest().body("Book not found.");
        }

        List<Double> ratings = ratingsRepository.findByBookId(bookId);


        double sum = ratings.stream().mapToDouble(Double::doubleValue).sum();
        double avgRating = sum / ratings.size();

        return new ResponseEntity<>(avgRating, HttpStatus.OK);
    }
}

