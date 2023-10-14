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
        // Collections.sort(books2);
        Collections.sort(books2, (book1, book2) -> book1.getTitle().compareTo(book2.getTitle()));

        for(Book a:books2)
			System.out.println(a.getTitle());
        return books2;
    }    
    // public int compareTo(Book a) {
	//     if(a.getTitle().compareTo(a.getTitle()) != 0)
	// 	    return a.getTitle().compareTo(a.getTitle());		    
    //     else return 0;
    // }

    // @GetMapping("/sort-genre") {

    // }

// 	private String name, genre, title;
// 	private String release_year;
//	private int id;
	
	
// 	public sortBy(String name, String release_year, String genre, String title) {
// 		setName(name);
// 		setRelease_year(release_year);
// 		setGenre(genre);
// 		setTitle(title);
// 	}
	
// 	public String toString() {
// 	return title + "  ||  " + name + "  ||  " + genre + "  ||  " + release_year;
// 	}
	
// 	private void setName(String name) {
// 		this.name = name;
// 	}
// 	private void setRelease_year(String release_year) {
// 		this.release_year = release_year;
// 	}
// 	private void setGenre(String genre) {
// 		this.genre = genre;
// 	}
// 	private void setTitle(String title) {
// 		this.title = title;
// 	}
	
// 	public String getName() {
// 		return name;
// 	}
// 	public String getRelease_year() {
// 		return release_year;
// 	}
// 	public String getGenre() {
// 		return genre;
// 	}
// 	public String getTitle() {
// 		return title;
// 	}
	
// 	public int compareTo(sortBy a) {
// 		if(this.title.compareTo(a.title) != 0)
// 			return this.title.compareTo(a.title);
// 		else return 0;
// 	}
    
}
