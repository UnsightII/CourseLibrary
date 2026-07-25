package com.application.courselibrary.service;

import com.application.courselibrary.entity.Author;
import com.application.courselibrary.entity.Book;
import com.application.courselibrary.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> findAllAuthor(){
        return authorRepository.findAll();
    }

    public Author findAuthorById(long id){
        Author author = authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Author not found"));
        return author;
    }

    public void createAuthor(Author author){
        authorRepository.save(author);
    }

    public void updateAuthor(Author author){
        authorRepository.save(author);
    }

    public void deleteBook(long id){
        Author author = authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Author not found"));
        authorRepository.deleteById(author.getId());
    }


}
