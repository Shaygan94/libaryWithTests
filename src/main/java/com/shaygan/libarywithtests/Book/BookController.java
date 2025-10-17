package com.shaygan.libarywithtests.Book;

import com.shaygan.libarywithtests.TestData.TestData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;
    private final TestData testData;

    public BookController(BookService bookService, TestData testData) {
        this.bookService = bookService;
        this.testData = testData;
    }

    @PostMapping
    public ResponseEntity<Book> save(@RequestBody BookDto bookDto){
        return ResponseEntity.ok(bookService.save(bookDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable Long id){
        var result = bookService.getBook(id);
        if(result == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        bookService.deleteBook(id);
        return ResponseEntity.ok("Book with id: " + id + " got deleted");
    }

    @GetMapping("/init")
    public ResponseEntity<String> init() {
        testData.createTestData();
        return ResponseEntity.ok("Books with faker initialized.");
    }


}
