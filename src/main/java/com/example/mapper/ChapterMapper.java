package com.example.mapper;

import com.example.controller.admin.api.ChapterApi;
import com.example.model.AuthorModel;
import com.example.model.BookModel;
import com.example.model.ChapterModel;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChapterMapper implements RowMapper<ChapterModel> {

    @Override
    public ChapterModel mapRow(ResultSet resultSet) {

        try {
            ChapterModel chapterModel = new ChapterModel();
            BookModel bookModel = new BookMapper().mapRow(resultSet);
//            chapterModel.setBookId((int) resultSet.getLong("bookId"))
            chapterModel.setBook(bookModel);
            chapterModel.setId((int) resultSet.getLong(18));
            chapterModel.setTitle(resultSet.getString(19));
            chapterModel.setSlug(resultSet.getString(20));
            chapterModel.setData(resultSet.getString(21));
            chapterModel.setChapterIndex((int) resultSet.getLong(23));
            chapterModel.setAudioUrl(resultSet.getString(24));
            chapterModel.setCreated_at(resultSet.getTimestamp(25));
            chapterModel.setUpdated_at(resultSet.getTimestamp(26));

            return chapterModel;
        } catch (SQLException e) {
            return null;
        }
    }
}
