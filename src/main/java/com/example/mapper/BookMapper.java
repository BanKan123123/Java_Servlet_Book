package com.example.mapper;

import com.example.model.BookModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<BookModel> {

    @Override
    public BookModel mapRow(ResultSet resultSet) {
        try {
            BookModel bookModel = new BookModel();
            bookModel.setId((int) resultSet.getLong("id"));
            bookModel.setTitle(resultSet.getString("title"));
            bookModel.setDescription(resultSet.getString("description"));
            bookModel.setImageThumbnail(resultSet.getString("imageThumbnail"));
            bookModel.setRate(resultSet.getFloat("rate"));
            bookModel.setAuthorId(resultSet.getInt("authorId"));
            bookModel.setCategories(resultSet.getString("categories"));
            bookModel.setCreated_at(resultSet.getTimestamp("created_at"));
            bookModel.setUpdated_at(resultSet.getTimestamp("updated_at"));
            return bookModel;
        } catch (SQLException e) {
            return null;
        }
    }
}
