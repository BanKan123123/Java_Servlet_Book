package com.example.model;

public class ChapterModel extends AbstractModel {

    private BookModel book;
    //    private int bookId;
    private int chapterIndex;

    private String title, slug, data, audioUrl;


    public ChapterModel() {
    }

    public ChapterModel(BookModel book, int chapterIndex, String title, String slug, String data, String audioUrl) {
        this.book = book;
        this.chapterIndex = chapterIndex;
        this.title = title;
        this.slug = slug;
        this.data = data;
        this.audioUrl = audioUrl;
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

    public BookModel getBook() {
        return book;
    }

    public void setBook(BookModel book) {
        this.book = book;
    }
}
