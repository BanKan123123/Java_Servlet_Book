package com.example.mapper;

import com.example.controller.admin.api.ChapterApi;
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

            chapterModel.setId((int) resultSet.getLong("id"));
            chapterModel.setTitle(resultSet.getString("title"));
            chapterModel.setSlug(resultSet.getString("slug"));
            chapterModel.setData(resultSet.getString("data"));
            chapterModel.setBookId((int) resultSet.getLong("bookId"));
            chapterModel.setChapterIndex((int) resultSet.getLong("chapterIndex"));
            chapterModel.setAudioUrl(resultSet.getString("audioUrl"));
            chapterModel.setCreated_at(resultSet.getTimestamp("created_at"));
            chapterModel.setUpdated_at(resultSet.getTimestamp("updated_at"));

            return chapterModel;
        } catch (SQLException e) {
            return null;
        }
    }
}
