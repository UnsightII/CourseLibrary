package com.application.courselibrary.controller;

import com.application.courselibrary.entity.Publisher;
import com.application.courselibrary.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PublisherController {
    @Autowired
    private PublisherService publisherService;

    @GetMapping("/publishers")
    public String findAllPublishers(Model model){
        List <Publisher> publisher = publisherService.findAllPublishers();
        model.addAttribute("publishers",publisher);
        return "publishers";
    }

    @GetMapping("/update-publisher/{id}")
    public String updatePublisher(@PathVariable long id,Model model){
        Publisher publisher = publisherService.findPublisherById(id);

        model.addAttribute("publisher",publisher);

        return "update-publisher";
    }

    @PostMapping("/save-update-publisher/{id}")
    public String saveUpdatePublisher(@PathVariable Long id,
                                      Publisher publisher,
                                      BindingResult result) {

        if (result.hasErrors()) {
            return "update-publisher";
        }

        publisherService.updatePublisher(publisher);

        return "redirect:/publishers";
    }

    @GetMapping("/remove-publisher/{id}")
    public String deletePublisher(@PathVariable long id){
        publisherService.deletePublisher(id);
        return "redirect:/publishers";
    }

    @GetMapping("/add-publisher")
    public String addPublisher(Model model){
        model.addAttribute("publisher",new Publisher());
        return "add-publisher";
    }

    @PostMapping("/save-publisher")
    public String savePublisher(Publisher publisher, BindingResult result) {

        if (result.hasErrors()) {
            return "add-publisher";
        }

        publisherService.createPublisher(publisher);

        return "redirect:/publishers";
    }

}
