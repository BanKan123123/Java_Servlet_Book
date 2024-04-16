package com.example.dao.impl;

import com.example.dao.IChapterDAO;
import com.example.mapper.ChapterMapper;
import com.example.model.ChapterModel;

import java.util.List;

public class ChapterDAO extends AbstractDAO<ChapterModel> implements IChapterDAO {
    @Override
    public List<ChapterModel> findAllChapter() {
        String sql = "SELECT * FROM books, author, chapter WHERE books.authorId = author.id AND books.id = chapter.bookId ORDER BY books.title ASC";
        return query(sql, new ChapterMapper());
    }

    @Override
    public ChapterModel findOneChapter(String slug) {
        String sql = "SELECT * FROM books, author, chapter WHERE books.authorId = author.id AND books.id = chapter.bookId AND chapter.slug = ? ORDER BY books.title ASC ";
        if (query(sql, new ChapterMapper(), slug).isEmpty() || query(sql, new ChapterMapper(), slug) == null) {
            return null;
        }
        return query(sql, new ChapterMapper(), slug).get(0);
    }

    @Override
    public List<ChapterModel> findChapterByQuery(String query) {
        String sql = "SELECT * FROM books, author, chapter WHERE books.authorId = author.id AND books.id = chapter.bookId AND chapter.title LIKE '%" + query + "%' ORDER BY books.title ASC";
        return query(sql, new ChapterMapper());
    }

    @Override
    public Long addChapter(ChapterModel chapterModel) {
        String sql = "INSERT INTO chapter(title, slug, data, bookId, chapterIndex, audioUrl, summary) VALUES (? , ? , ? , ? , ? , ?, ?)";
        return insert(sql, chapterModel.getTitle(), chapterModel.getSlug(), chapterModel.getData(), chapterModel.getBook().getId(), chapterModel.getChapterIndex(), chapterModel.getAudioUrl(), chapterModel.getSummary());
    }

    @Override
    public void updateChapter(ChapterModel chapterModel, String slug) {
        String sql = "UPDATE chapter SET title = ?, slug = ?, data = ?, bookId = ?, chapterIndex = ?, audioUrl = ?, summary = ? WHERE slug = ?";
        update(sql, chapterModel.getTitle(), chapterModel.getSlug(), chapterModel.getData(), chapterModel.getBook().getId(), chapterModel.getChapterIndex(), chapterModel.getAudioUrl(), chapterModel.getSummary(), slug);
    }

    @Override
    public void deleteChapter(String slug) {
        String sql = "DELETE FROM chapter WHERE slug = ?";
        update(sql, slug);
    }
}
