package com.shaygan.libarywithtests.Book;

import com.shaygan.libarywithtests.Author.Author;
import com.shaygan.libarywithtests.Author.AuthorService;
import com.shaygan.libarywithtests.Location.LocationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    private final BookRepo bookRepo;
    private final LocationService locationService;
    private final AuthorService authorService;
    public BookService(BookRepo bookRepo, LocationService locationService, AuthorService authorService) {
        this.bookRepo = bookRepo;
        this.locationService = locationService;
        this.authorService = authorService;
    }

 public Book save(BookDto bookDto) {
        var location = locationService.getById(bookDto.locationId());
        List<Author> authors = new ArrayList<>();
     bookDto.authorIds().forEach(a -> authors.add(authorService.getById(a)));
     var newBook = new Book(bookDto.title(), location, authors);
     return bookRepo.save(newBook);
 }

 public Book getBook(Long id) {
        return bookRepo.findById(id).orElse(null);

 }

 public void deleteBook(Long id) {
        bookRepo.deleteById(id);
 }
}
