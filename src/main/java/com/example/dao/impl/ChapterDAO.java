package com.example.dao.impl;

import com.example.dao.IChapterDAO;
import com.example.mapper.ChapterMapper;
import com.example.mapper.RowMapper;
import com.example.model.ChapterModel;

import java.util.List;

public class ChapterDAO extends AbstractDAO<ChapterModel> implements IChapterDAO {
    @Override
    public List<ChapterModel> findAllChapter() {
        String sql = "SELECT * FROM chapter ORDER BY titleChapter ASC";
        return query(sql, new ChapterMapper());
    }

    @Override
    public ChapterModel findOneChapter(int id) {
        String sql = "SELECT * FROM chapter WHERE id = ?";
        return query(sql, new ChapterMapper(), id).get(0);
    }

    @Override
    public Long addChapter(ChapterModel chapterModel) {
        String sql = "INSERT INTO chapter(titleChapter, slugChapter, data, bookId, chapterIndex, audioUrl)";
        return insert (sql, chapterModel.getTitleChapter(), chapterModel.getSlugChapter(), chapterModel.getData(), chapterModel.getBookId(), chapterModel.getChapterIndex(), chapterModel.getAudioUrl());
    }

    @Override
    public void updateChapter(ChapterModel chapterModel, int id) {
        String sql = "UPDATE chapter SET titleChapter = ?, slugChapter = ?, data = ?, bookId = ?, chapterIndex = ?, audioUrl = ?";
        update(sql, chapterModel.getTitleChapter(), chapterModel.getSlugChapter(), chapterModel.getData(), chapterModel.getBookId(), chapterModel.getChapterIndex(), chapterModel.getAudioUrl(), id);
    }

    @Override
    public void deleteChapter(int id) {
        String sql = "DELETE FROM chapter WHERE id = ?";
        update(sql, id);
    }
}
