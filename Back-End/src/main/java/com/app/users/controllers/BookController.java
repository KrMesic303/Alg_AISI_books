package com.app.users.controllers;

import com.app.users.dto.BookDTO;
import com.app.users.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public List<BookDTO> getBooks() {
        return bookService.findAll();
    }

    @PostMapping("/book")
    public BookDTO createBook(@RequestBody BookDTO book) {
        return bookService.add(book);
    }

    @PutMapping("/book/{id}")
    public BookDTO updateBook(@PathVariable(name = "id") int id, @RequestBody BookDTO book) {
        return bookService.update(book, id);
    }

    @DeleteMapping("/book/{id}")
    public BookDTO deleteBook(@PathVariable int id) {
        return bookService.remove(id);
    }
}
