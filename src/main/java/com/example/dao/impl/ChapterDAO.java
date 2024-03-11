package com.example.dao.impl;

import com.example.dao.IChapterDAO;
import com.example.mapper.ChapterMapper;
import com.example.model.ChapterModel;

import java.util.List;

public class ChapterDAO extends AbstractDAO<ChapterModel> implements IChapterDAO {
    @Override
    public List<ChapterModel> findAllChapter() {
        String sql = "SELECT * FROM chapter ORDER BY title ASC";
        return query(sql, new ChapterMapper());
    }

    @Override
    public ChapterModel findOneChapter(String slug) {
        String sql = "SELECT * FROM chapter WHERE slug = ?";
        if (query(sql, new ChapterMapper(), slug).isEmpty()) {
            return null;
        }
        return query(sql, new ChapterMapper(), slug).get(0);
    }

    @Override
    public Long addChapter(ChapterModel chapterModel) {
        String sql = "INSERT INTO chapter(title, slug, data, bookId, chapterIndex, audioUrl) VALUES (? , ? , ? , ? , ? , ?)";
        return insert(sql, chapterModel.getTitle(), chapterModel.getSlug(), chapterModel.getData(), chapterModel.getBookId(), chapterModel.getChapterIndex(), chapterModel.getAudioUrl());
    }

    @Override
    public void updateChapter(ChapterModel chapterModel, String slug) {
        String sql = "UPDATE chapter SET title = ?, slug = ?, data = ?, bookId = ?, chapterIndex = ?, audioUrl = ? WHERE slug = ?";
        update(sql, chapterModel.getTitle(), chapterModel.getSlug(), chapterModel.getData(), chapterModel.getBookId(), chapterModel.getChapterIndex(), chapterModel.getAudioUrl(), slug);
    }

    @Override
    public void deleteChapter(String slug) {
        String sql = "DELETE FROM chapter WHERE slug = ?";
        update(sql, slug);
    }
}
