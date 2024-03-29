package com.example.dao;

import com.example.model.BookModel;

import java.util.List;

public interface IBookDAO extends GenericDAO<BookModel>{

    List<BookModel> findAllBooks();
    BookModel findOneBookById(int id);

    BookModel findOneBookBySlug (String slug);

    Long addBook(BookModel bookModel);

    void updateBook(BookModel bookModel, String id);

    void deleteBook(String slug);

}
