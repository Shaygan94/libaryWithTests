package com.shaygan.libarywithtests;

import com.shaygan.libarywithtests.Author.Author;
import com.shaygan.libarywithtests.Author.AuthorService;
import com.shaygan.libarywithtests.Book.BookDto;
import com.shaygan.libarywithtests.Book.BookService;
import com.shaygan.libarywithtests.Location.Location;
import com.shaygan.libarywithtests.Location.LocationService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional  //
public class BookServiceIT extends BaseIntegrationTest {

    @Autowired private BookService bookService;
    @Autowired private AuthorService authorService;
    @Autowired private LocationService locationService;

    @Test
   void shouldSaveAndRetrieveBookWithAuthorsAndLocation(){

        //Given - opprett testdata
        var location = locationService.save(new Location("Address One", "Room One", "Shelf One"));
        var author = authorService.save(new Author("First", "Last", "<EMAIL>", null));

        var bookDto = new BookDto(null, "Title One", location.getId(), List.of(author.getId()));

        //When - lagre bok og hent den igjen
        var savedBook = bookService.save(bookDto);


        //Then - verifiser at alt er lagret korrekt
        assertThat(savedBook).isNotNull();
        assertThat(savedBook.getTitle()).isEqualTo("Title One");
        assertThat(savedBook.getLocation().getAddress()).isEqualTo("Address One");
        assertThat(savedBook.getAuthors().getFirst().getFirstName()).isEqualTo("First");
        assertThat(savedBook.getAuthors().getFirst().getLastName()).isEqualTo("Last");
    }
}
