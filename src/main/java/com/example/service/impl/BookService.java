package com.example.service.impl;

import com.example.dao.IBookDAO;
import com.example.dao.impl.BookDAO;
import com.example.model.BookModel;
import com.example.service.IBookService;
import com.fasterxml.jackson.annotation.JacksonInject;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

public class BookService implements IBookService {
    private BookDAO bookDAO = new BookDAO();
    public BookService() {
    }
    @Override
    public BookModel findOneBookById(int id) {
        BookModel bookModel = bookDAO.findOneBookById(id);
        return bookModel;
    }

    @Override
    public List<BookModel> findAllBooks() {
        ArrayList<BookModel> listBooks = (ArrayList<BookModel>) bookDAO.findAllBooks();
        return listBooks;
    }

    @Override
    public BookModel save(BookModel bookModel) {
        bookDAO.addBook(bookModel);
        return bookModel;
    }

    @Override
    public BookModel delete(int id) {
        bookDAO.deleteBook(id);
        return this.findOneBookById(id);
    }
    @Override
    public BookModel update(BookModel bookModel, int id) {
        bookDAO.updateBook(bookModel, id);
        return bookModel;
    }
}
