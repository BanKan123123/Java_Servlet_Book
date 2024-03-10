package com.example.service;

import com.example.model.BookModel;

import java.util.List;

public interface IBookService {
    BookModel findOneBookById(int id);

    List<BookModel> findAllBooks();

    BookModel save(BookModel bookModel);

    void delete(int id);

    BookModel update(BookModel bookModel, int id);
}
