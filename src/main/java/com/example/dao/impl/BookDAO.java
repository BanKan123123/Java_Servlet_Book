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
        String sql = "SELECT * FROM chapter, books, author WHERE books.id = ? AND chapter.bookId = books.id AND books.authorId = author.idAuthor ORDER BY chapter.chapterIndex ASC";
        return query(sql, new BookMapper(), id).get(0);
    }

    @Override
    public Long addBook(BookModel bookModel) {
        String sql = "INSERT INTO books (title, description, imageThumbnail, rate, authorId, categories) VALUES (?, ? ,? ,? ,? ,?)";
        return insert(sql, bookModel.getTitle(), bookModel.getDescription(), bookModel.getImageThumbnail(), bookModel.getRate(), bookModel.getAuthorId(), bookModel.getCategories());
    }

    @Override
    public void updateBook(BookModel bookModel, int id) {
        String sql = "UPDATE books SET `title` = ?, `description` = ?, `imageThumbnail` = ?, `rate` = ?, `authorId` = ?, categories = ? WHERE `id` = ?";
        update(sql, bookModel.getTitle(), bookModel.getDescription(), bookModel.getImageThumbnail(), bookModel.getRate(), bookModel.getAuthorId(), bookModel.getCategories(), id);
    }

    @Override
    public void deleteBook(int id) {
        String sql = "DELETE FROM books WHERE id = ?";
        update(sql, id);
    }

}
