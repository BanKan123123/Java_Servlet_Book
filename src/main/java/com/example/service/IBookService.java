package com.example.service;

import com.example.model.BookModel;

import java.util.List;

public interface IBookService {
    BookModel findOneBookById(int id);

    List<BookModel> findAllBooks();

    BookModel findOneBookBySlug (String slug);

    List<BookModel> findBookByQuery(String query);

    List<BookModel> rangeBookByMonth();

    BookModel save(BookModel bookModel);

    void delete(String slug);

    BookModel update(BookModel bookModel, String slug);
}
