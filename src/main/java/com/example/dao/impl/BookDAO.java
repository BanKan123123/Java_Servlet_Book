package com.example.dao.impl;

import com.example.dao.IBookDAO;
import com.example.mapper.BookMapper;
import com.example.model.BookModel;
import com.example.utils.ConfigDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class BookDAO extends AbstractDAO<BookModel> implements IBookDAO {
    @Override
    public List<BookModel> findAllBooks() {
        String sql = "SELECT * FROM books ORDER BY books.title ASC";
        return query(sql, new BookMapper());
    }

    @Override
    public BookModel findOneBookById(int id) {
        String sql = "SELECT * FROM books WHERE books.id = ?";
        return query(sql, new BookMapper(), id).get(0);
    }

    @Override
    public Long addBook(BookModel bookModel) {
        String sql = "INSERT INTO books (title, slug , description, imageThumbnail, rate, liked, authorId, categories, quantity) VALUES (?, ? ,? ,? ,? ,?, ? ,? , ?)";
        return insert(sql, bookModel.getTitle(), bookModel.getSlug(), bookModel.getDescription(), bookModel.getImageThumbnail(), bookModel.getRate(), bookModel.getLiked(), bookModel.getAuthorId(), bookModel.getCategories(), bookModel.getQuantity());
    }

    @Override
    public void updateBook(BookModel bookModel, int id) {
        String sql = "UPDATE books SET `title` = ?, `slug` = ?, `description` = ?, `imageThumbnail` = ?, `rate` = ?, `liked` = ? ,`authorId` = ?, categories = ?, `quantity` = ? WHERE `id` = ?";
        update(sql, bookModel.getTitle(), bookModel.getSlug(), bookModel.getDescription(), bookModel.getImageThumbnail(), bookModel.getRate(), bookModel.getLiked(), bookModel.getAuthorId(), bookModel.getCategories(), bookModel.getQuantity(), id);
    }

    @Override
    public void deleteBook(int id) {
        String sql = "DELETE FROM books WHERE id = ?";
        update(sql, id);
    }

}
