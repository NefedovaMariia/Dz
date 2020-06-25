package com.controller;
import com.domain.Author;
import com.domain.Book;
import com.domain.Comment;
import com.domain.Genre;
import com.service.BookService;
import com.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final CommentService commentService;

    @GetMapping("/")
    public String books(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/addbook")
    public String addBook(Model model) {
        model.addAttribute("book", new Book(new Author(), new Genre()));
        return "edit";
    }

    @PostMapping("/addbook")
    public String addBook(@ModelAttribute Book book) {
        bookService.addNewBook(book);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookService.deleteById(id);
        return "redirect:/";
    }
    @GetMapping("/view/{id}")
    public String showBook(@PathVariable("id") Long id, Model model) {
        List<Comment> comments = commentService.findByBookId(id);
        model.addAttribute("comments", comments);
        return "view";
    }
}
