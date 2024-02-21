package com.example.dao;

import com.example.model.BookModel;

import java.util.List;

public interface IBookDAO extends GenericDAO<BookModel>{

    List<BookModel> findAllBooks();
    BookModel findOneBookById(int id);

    Long addBook(BookModel bookModel);

    void updateBook(BookModel bookModel, int id);

    void deleteBook(int bookModel);

}
