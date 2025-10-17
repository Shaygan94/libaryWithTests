package com.shaygan.libarywithtests.Author;

import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    private final AuthorRepo authorRepo;

    public AuthorService(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    public Author save(Author author){
        return authorRepo.save(author);
    }

    public Author getById(Long id){
        return authorRepo.findById(id).orElse(null);
    }

    public void deleteById(Long id){
        authorRepo.deleteById(id);
    }


}
