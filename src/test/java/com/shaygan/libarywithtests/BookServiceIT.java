package com.shaygan.libarywithtests;

import com.shaygan.libarywithtests.Author.Author;
import com.shaygan.libarywithtests.Author.AuthorService;
import com.shaygan.libarywithtests.Book.BookDto;
import com.shaygan.libarywithtests.Book.BookService;
import com.shaygan.libarywithtests.Location.Location;
import com.shaygan.libarywithtests.Location.LocationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BookServiceIT extends BaseIntegrationTest {

    @Autowired private BookService bookService;
    @Autowired private AuthorService authorService;
    @Autowired private LocationService locationService;

    @Test
    void testOfContainer () {
        var newLocation = locationService.save(new Location("Place", "Room", "Shelf"));
        var newAuthor = new Author("John", "Doe", "ja", null);
        authorService.save(newAuthor);
        var newBook = new BookDto(null, "Book Title", newLocation.getId(), List.of(newAuthor.getId()));
        var result = bookService.save(newBook);
        assert result.getAuthors().size() == 1;
    }
}
