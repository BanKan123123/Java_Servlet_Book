package com.example.mapper;

import com.example.model.AuthorModel;
import com.example.model.BookModel;
import com.example.model.ChapterModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ChapterMapper implements RowMapper<ChapterModel> {

    @Override
    public ChapterModel mapRow(ResultSet resultSet) {

        try {
            ChapterModel chapterModel = new ChapterModel();
            AuthorModel authorModel = new AuthorModel();

            int bookId = ((int) resultSet.getLong(1));
            String titleBook = (resultSet.getString(2));
            String slugBook = (resultSet.getString(3));
            String descriptionBook = (resultSet.getString(4));
            String imageThumbnail = (resultSet.getString(5));
            float rateBook = (resultSet.getFloat(6));
            int liked = (resultSet.getInt(7));
            int quantityBook = (resultSet.getInt(9));
            Timestamp createdAtBook = (resultSet.getTimestamp(10));
            Timestamp upodatedAtBook = (resultSet.getTimestamp(11));
            authorModel.setId(resultSet.getInt(12));
            authorModel.setName(resultSet.getString(13));
            authorModel.setSlug(resultSet.getString(14));
            authorModel.setCreated_at(resultSet.getTimestamp(15));
            authorModel.setUpdated_at(resultSet.getTimestamp(16));

            BookModel bookModel = new BookModel(bookId, createdAtBook, upodatedAtBook, titleBook, slugBook, descriptionBook, imageThumbnail, rateBook, authorModel, liked, quantityBook);

            chapterModel.setBook(bookModel);
            chapterModel.setId((int) resultSet.getLong(17));
            chapterModel.setTitle(resultSet.getString(18));
            chapterModel.setSlug(resultSet.getString(19));
            chapterModel.setData(resultSet.getString(20));
            chapterModel.setChapterIndex((int) resultSet.getLong(22));
            chapterModel.setAudioUrl(resultSet.getString(23));
            chapterModel.setCreated_at(resultSet.getTimestamp(24));
            chapterModel.setUpdated_at(resultSet.getTimestamp(25));

            return chapterModel;
        } catch (SQLException e) {
            return null;
        }
    }
}
