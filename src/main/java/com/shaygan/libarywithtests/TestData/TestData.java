package com.shaygan.libarywithtests.TestData;

import com.github.javafaker.Faker;
import com.shaygan.libarywithtests.Author.Author;
import com.shaygan.libarywithtests.Author.AuthorRepo;
import com.shaygan.libarywithtests.Book.Book;
import com.shaygan.libarywithtests.Book.BookRepo;
import com.shaygan.libarywithtests.Location.Location;
import com.shaygan.libarywithtests.Location.LocationRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class TestData {

    private final LocationRepo locationRepo;
    private final BookRepo bookRepo;
    private final AuthorRepo authorRepo;
    private final Faker faker = new Faker();

    public TestData(LocationRepo locationRepo, BookRepo bookRepo, AuthorRepo authorRepo) {
        this.locationRepo = locationRepo;
        this.bookRepo = bookRepo;
        this.authorRepo = authorRepo;
    }

    private List<Author> createAuthors(){
        var authorList = new ArrayList<Author>();
        for (int i = 0; i < 20; i++) {
            authorList.add(authorRepo.save(new Author(
                    faker.name().firstName(),
                    faker.name().lastName(),
                    faker.internet().emailAddress(),
                    null
            )));
        }
        return authorList;
    }

    public void createTestData(){
        var location = locationRepo.save(new Location("Address One", "Room One", "Shelf One"));
        var authors = createAuthors();

        for (int i = 0; i < 50; i++){
            var numAuthors = new Random().nextInt(3) + 1;
            var bookAuthors = new ArrayList<Author>();
            for (int j = 0; j < numAuthors; j++) {
                var randomAuthor = new Random().nextInt(authors.size());
                bookAuthors.add(authors.get(randomAuthor));
            }
            bookRepo.save(
                    new Book(
                            faker.book().title(),
                            location,
                            bookAuthors

                    )
            );
        }
    }
}
