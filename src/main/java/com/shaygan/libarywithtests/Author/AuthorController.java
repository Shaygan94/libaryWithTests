package com.shaygan.libarywithtests.Author;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/author")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping()
    public ResponseEntity<Author> save(@RequestBody Author author){
        return ResponseEntity.ok(authorService.save(author));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getById(@PathVariable Long id){
        var result = authorService.getById(id);
        if(result == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id){
        authorService.deleteById(id);
        return ResponseEntity.ok("Author with id: " + id + " got deleted");
    }
}

