package com.app.users.service;

import com.app.users.models.Book;
import com.app.users.dao.BookDAO;
import com.app.users.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class BookService implements IBookService {

    @Autowired
    private BookDAO dao;


    @Override
    public BookDTO add(BookDTO book) {
        book.setAvailable(true);
        return fromBook(dao.save(toBook(book)));
    }

    @Override
    public BookDTO remove(int id) {
        Optional<Book> book = dao.findById(id);
        if(book.isPresent())
            dao.deleteById(id);
        else
            throw new RuntimeException("book not found");
        return  this.fromBook(book.get());
    }

    @Override
    public BookDTO update(BookDTO book, int id) {
        Optional<Book> oldBook = dao.findById(id);
        if(oldBook.isPresent()){
            BookDTO updatedBook = this.fromBook(oldBook.get());
            updatedBook.setName(book.getName());
            updatedBook.setAuthor(book.getAuthor());
            updatedBook.setPrice(book.getPrice());
            dao.save(this.toBook(updatedBook));
            return updatedBook;
        }
        else
            throw new RuntimeException("User not found");
    }

    @Override
    public List<BookDTO> findAll() {
        return  dao.findAll().stream()
                .map(this::fromBook)
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO findBook(int id) {
        Optional<Book> book = dao.findById(id);
        if (!book.isEmpty())
            return fromBook(book.get());
        return null;
    }

    public Book toBook(BookDTO book)
    {
        return Book.builder()
                .name(book.getName())
                .available(book.isAvailable())
                .isbn(book.getIsbn())
                .price(book.getPrice())
                .author(book.getAuthor())
                .build();
    }

    public BookDTO fromBook(Book book)
    {
        return BookDTO.builder()
                .name(book.getName())
                .available(book.isAvailable())
                .isbn(book.getIsbn())
                .author(book.getAuthor())
                .price(book.getPrice())
                .build();
    }
}
