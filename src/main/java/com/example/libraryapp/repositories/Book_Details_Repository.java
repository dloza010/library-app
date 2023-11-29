package com.example.libraryapp.repositories;
import com.example.libraryapp.entity.Book.Book_Author_Intermediate;
import com.example.libraryapp.entity.Book.Book_Details;
import com.example.libraryapp.entity.Client.Client;
import jakarta.persistence.*;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

@Repository
public interface Book_Details_Repository extends JpaRepository<Book_Details, Long>{

//
//    //find all book details from book details entity along with author and publisher
//    //to be implemented
////    public List<Object[]> findBookById(long ISBN){
////
////        return ;
////    }
//

//
    ///return all book details by ISBN

    @Query(value = "SELECT * FROM Book_Details ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Book_Details findRandomBook();



    //return full book details
    @Query("SELECT a, b, c, p FROM Book_Details a, Book_Author_Intermediate b,"
            + " Author_Details c, Publisher p WHERE a.ISBN = ?1 AND a.ISBN = b.book_details.ISBN AND"
            + " b.author_details.author_id = ?2 AND b.author_details.author_id = c.author_id AND a.publisher.publisher_id = p.publisher_id"
            + " AND p.publisher_id = ?3")
    public List findFullBookDetails(long ISBN, long author_id, long publisher_id);
    }
//}
