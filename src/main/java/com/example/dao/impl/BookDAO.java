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

    public BookDAO() {
    }

    @Override
    public List<BookModel> findAllBooks() {
//        ArrayList<BookModel> books = new ArrayList<>();
        String sql = "SELECT * FROM books, author WHERE books.authorId = author.idAuthor ORDER BY books.title ASC";
        return query(sql, new BookMapper());
//        try (Connection conn = ConfigDB.provideConnection()) {
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ResultSet resultSet = ps.executeQuery();
//
//            while (resultSet.next()) {
//                BookModel bookModel = this.setFieldBook(resultSet);
//                books.add(bookModel);
//            }
//
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//
//        return books;
    }

//    public BookModel setFieldBook(ResultSet resultSet) throws SQLException {
//        int id = resultSet.getInt("id");
//        String title = resultSet.getString("title");
//        String desc = resultSet.getString("description");
//        String imageThumbnail = resultSet.getString("imageThumbnail");
//        float rate = resultSet.getFloat("rate");
//        int authorId = resultSet.getInt("authorId");
//        String categories = resultSet.getString("categories");
//        Date createdAt = resultSet.getDate("created_at");
//        Date updatedAt = resultSet.getDate("updated_at");
//        return new BookModel(id, title, desc, imageThumbnail, rate, authorId, categories, createdAt, updatedAt);
//    }
//
//    public void getFieldBook(PreparedStatement statement, BookModel bookModel) throws SQLException {
//        statement.setString(1, bookModel.getTitle());
//        statement.setString(2, bookModel.getDescription());
//        statement.setString(3, bookModel.getImageThumbnail());
//        statement.setFloat(4, bookModel.getRate());
//        statement.setInt(5, bookModel.getAuthorId());
//        statement.setString(6, bookModel.getCategories());
//    }

    @Override
    public BookModel findOneBookById(int id) {
        String sql = "SELECT * FROM chapter, books, author WHERE books.id = ? AND chapter.bookId = books.id AND books.authorId = author.idAuthor ORDER BY chapter.chapterIndex ASC";
        return query(sql, new BookMapper(), id).get(0);
        //        try (Connection conn = ConfigDB.provideConnection()) {
//            PreparedStatement statement = conn.prepareStatement(sql);
//            statement.setInt(1, id);
//            ResultSet resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                return this.setFieldBook(resultSet);
//            }
//
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        return null;
    }

    @Override
    public Long addBook(BookModel bookModel) {
        String sql = "INSERT INTO books (title, description, imageThumbnail, rate, authorId, categories) VALUES (?, ? ,? ,? ,? ,?)";
//        try (Connection conn = ConfigDB.provideConnection()) {
//            PreparedStatement statement = conn.prepareStatement(sql);
//            getFieldBook(statement, bookModel);
//            statement.executeUpdate();
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
        return insert(sql, bookModel.getTitle(), bookModel.getDescription(), bookModel.getImageThumbnail(), bookModel.getRate(), bookModel.getAuthorId(), bookModel.getCategories());
    }

    @Override
    public void updateBook(BookModel bookModel, int id) {
        String sql = "UPDATE books SET `title` = ?, `description` = ?, `imageThumbnail` = ?, `rate` = ?, `authorId` = ?, categories = ? WHERE `id` = ?";

//        try (Connection conn = ConfigDB.provideConnection()) {
//            PreparedStatement statement = conn.prepareStatement(sql);
//            getFieldBook(statement, bookModel);
//            statement.setInt(7, id);
//            statement.executeUpdate();
//        } catch (SQLException | ClassNotFoundException e) {
//
//            throw new RuntimeException(e);
//        }
        update(sql, bookModel.getTitle(), bookModel.getDescription(), bookModel.getImageThumbnail(), bookModel.getRate(), bookModel.getAuthorId(), bookModel.getCategories(), id);
    }

    @Override
    public void deleteBook(int id) {
        String sql = "DELETE FROM books WHERE id = ?";
//        try (Connection conn = ConfigDB.provideConnection()) {
//            PreparedStatement statement = conn.prepareStatement(sql);
//            statement.setInt(1, id);
//            statement.executeUpdate();
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
        update(sql, id);
    }

}
