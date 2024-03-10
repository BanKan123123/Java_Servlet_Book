package com.example.model;

import java.sql.Timestamp;
public class ChapterModel extends AbstractModel {

    private int bookId, chapterIndex;

    private String title, slug, data, audioUrl;


    public ChapterModel() {
    }

    public ChapterModel(int id, Timestamp created_at, Timestamp updated_at, int bookId, int chapterIndex, String title, String slug, String data, String audioUrl) {
        super(id, created_at, updated_at);
        this.bookId = bookId;
        this.chapterIndex = chapterIndex;
        this.title = title;
        this.slug = slug;
        this.data = data;
        this.audioUrl = audioUrl;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getChapterIndex() {
        return chapterIndex;
    }

    public void setChapterIndex(int chapterIndex) {
        this.chapterIndex = chapterIndex;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }
}
