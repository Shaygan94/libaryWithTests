package com.shaygan.libarywithtests;

import com.shaygan.libarywithtests.Author.Author;
import com.shaygan.libarywithtests.Author.AuthorService;
import com.shaygan.libarywithtests.Book.Book;
import com.shaygan.libarywithtests.Book.BookDto;
import com.shaygan.libarywithtests.Book.BookRepo;
import com.shaygan.libarywithtests.Book.BookService;
import com.shaygan.libarywithtests.Location.Location;
import com.shaygan.libarywithtests.Location.LocationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BookServiceUnitTest {

    @Autowired
    private BookService bookService;

    @MockitoBean
    private BookRepo bookRepo;

    @MockitoBean
    private LocationService locationService;

    @MockitoBean
    private AuthorService authorService;

    @Test
    void shouldSaveBook(){
        var location = new Location("Test Address", "Test Room", "Test Shelf");
        location.setId(1L);
        var author = new Author("Test First", "Test Last", "<EMAIL>", null);
        author.setId(1L);
        var bookDto = new BookDto(null, "Test Title", location.getId(), List.of(author.getId()));
        var expectedBook = new Book("Test Book", location, List.of(author));

        when(locationService.getById(location.getId())).thenReturn(location);
        when(authorService.getById(1L)).thenReturn(author);
        when(bookRepo.save(any(Book.class))).thenReturn(expectedBook);

        var savedBook = bookService.save(bookDto);

        assert savedBook != null;
        assert savedBook.getTitle().equals("Test Book");

    }

}
