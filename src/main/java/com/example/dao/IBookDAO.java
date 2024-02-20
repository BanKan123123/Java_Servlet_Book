package com.example.dao;

import com.example.model.BookModel;

import java.awt.print.Book;
import java.util.List;

public interface IBookDAO {

    List<BookModel> findAllBooks();
    BookModel findOneBookById(int id);

    void addBook(BookModel bookModel);

    void updateBook(BookModel bookModel, int id);

    void deleteBook(int bookModel);

}
