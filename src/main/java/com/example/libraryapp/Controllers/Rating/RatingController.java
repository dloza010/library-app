package com.example.libraryapp.Controllers.Rating;

import com.example.libraryapp.repositories.Book_Details_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.libraryapp.entity.Comments.Ratings;
import com.example.libraryapp.entity.Comments.Comments;
import com.example.libraryapp.repositories.Comments_Repository;
import com.example.libraryapp.repositories.Ratings_Repository;
import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {
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
    public ResponseEntity<?> commentBook(@RequestBody Comments comment) {
        Comments savedComment = commentsRepository.save(comment);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/comments/{bookId}")
    public ResponseEntity<?> getComments(@PathVariable long bookId) {
        List<Comments> comments = commentsRepository.findCommentsByBook_details(bookId);

        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/averageRating/{bookId}")
    public ResponseEntity<?> getAverageRating(@PathVariable long bookId) {
        List<Ratings> ratings = ratingsRepository.findRatingsByBook_details(bookId);

        if (ratings.isEmpty()) {
            return new ResponseEntity<>(0, HttpStatus.OK);
        }

        double sum = ratings.stream().mapToDouble(Ratings::getRating).sum();
        double avgRating = sum / ratings.size();

        return new ResponseEntity<>(avgRating, HttpStatus.OK);
    }
}

