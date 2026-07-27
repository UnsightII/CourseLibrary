package com.application.courselibrary.controller;

import com.application.courselibrary.entity.Author;
import com.application.courselibrary.entity.Category;
import com.application.courselibrary.entity.Publisher;
import com.application.courselibrary.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AuthorController {
    @Autowired
    public AuthorService authorService;

    @GetMapping("/authors")
    public String listAuthors(Model model) {

        List<Author> authors = authorService.findAllAuthor();
        model.addAttribute("authors", authors);

        return "authors";
    }
    @GetMapping("/update-author/{id}")
    public String updateAuthor(@PathVariable long id,Model model){
        Author author = authorService.findAuthorById(id);
        model.addAttribute("author",author);
        return "update-author";
    }

    @PostMapping("/save-update-author/{id}")
    public String saveUpdateAuthor(@PathVariable long id, Author author, BindingResult result) {

        if (result.hasErrors()) {
            return "update-author";
        }

        authorService.updateAuthor(author);

        return "redirect:/authors";
    }
        @GetMapping("/remove-author/{id}")
        public String removeAuthor(@PathVariable long id){
            authorService.deleteAuthor(id);
            return "redirect:/authors";
        }

        @GetMapping("/add-author")
        public String addAuthor(Model model){
        model.addAttribute("author",new Author());
        return "add-author";
        }

        @PostMapping("/save-author")
        public String saveAuthor(Author author,BindingResult result){
            if (result.hasErrors()) {
                return "add-author";
            }

            authorService.createAuthor(author);
            return "redirect:/authors";
        }


//    @GetMapping("/add-author")
//    public String addAuthor(Model model){
//        Author author = authorService.createAuthor(author);
//
//    }

}
