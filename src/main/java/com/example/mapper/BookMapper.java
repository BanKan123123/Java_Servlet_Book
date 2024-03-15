package com.example.mapper;

import com.example.model.AuthorModel;
import com.example.model.BookModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookMapper implements RowMapper<BookModel> {

    @Override
    public BookModel mapRow(ResultSet resultSet) {
        try {
            BookModel bookModel = new BookModel();
            AuthorModel authorModel = new AuthorModel();
            bookModel.setId((int) resultSet.getLong(1));
            bookModel.setTitle(resultSet.getString(2));
            bookModel.setSlug(resultSet.getString(3));
            bookModel.setDescription(resultSet.getString(4));
            bookModel.setImageThumbnail(resultSet.getString(5));
            bookModel.setRate(resultSet.getFloat(6));
            bookModel.setLiked(resultSet.getInt(7));
//            bookModel.setAuthorId(resultSet.getInt("authorId"));
            bookModel.setCategories(resultSet.getString(9));
            bookModel.setQuantity(resultSet.getInt(10));
            bookModel.setCreated_at(resultSet.getTimestamp(11));
            bookModel.setUpdated_at(resultSet.getTimestamp(12));

            authorModel.setId(resultSet.getInt(13));
            authorModel.setName(resultSet.getString(14));
            authorModel.setSlug(resultSet.getString(15));
            authorModel.setCreated_at(resultSet.getTimestamp(16));
            authorModel.setUpdated_at(resultSet.getTimestamp(17));

            bookModel.setAuthors(authorModel);

            return bookModel;
        } catch (SQLException e) {
            return null;
        }
    }
}
