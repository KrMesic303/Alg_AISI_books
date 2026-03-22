package com.app.users.service;

import com.app.users.dto.BookDTO;

import java.util.List;

public interface IBookService {

    public BookDTO add(BookDTO user);
    public BookDTO remove(int id);
    public BookDTO update(BookDTO user, int id);
    public List<BookDTO> findAll();
    public BookDTO findBook(int id);
}
