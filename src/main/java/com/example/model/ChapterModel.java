package com.example.model;

import java.sql.Timestamp;
public class ChapterModel extends AbstractModel {

    private int bookId, chapterIndex;

    private String titleChapter, slugChapter, data, audioUrl;

    public ChapterModel(int id, Timestamp created_at, Timestamp updated_at, int bookId, int chapterIndex, String titleChapter, String slugChapter, String data, String audioUrl) {
        super(id, created_at, updated_at);
        this.bookId = bookId;
        this.chapterIndex = chapterIndex;
        this.titleChapter = titleChapter;
        this.slugChapter = slugChapter;
        this.data = data;
        this.audioUrl = audioUrl;
    }

    public ChapterModel() {
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

    public String getTitleChapter() {
        return titleChapter;
    }

    public void setTitleChapter(String titleChapter) {
        this.titleChapter = titleChapter;
    }

    public String getSlugChapter() {
        return slugChapter;
    }

    public void setSlugChapter(String slugChapter) {
        this.slugChapter = slugChapter;
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
