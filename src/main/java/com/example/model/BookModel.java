package com.example.model;

import java.util.Date;

public class BookModel extends AbstractModel {
    private String title, description, imageThumbnail, categories;
    private float rate;
    private int authorId;

    public BookModel() {

    }

    public BookModel(int id, String title, String description, String imageThumbnail, float rate, int authorId, String categories, Date createdAt, Date updatedAt) {
        super(id, createdAt, updatedAt);
        this.title = title;
        this.description = description;
        this.imageThumbnail = imageThumbnail;
        this.categories = categories;
        this.rate = rate;
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageThumbnail() {
        return imageThumbnail;
    }

    public void setImageThumbnail(String imageThumbnail) {
        this.imageThumbnail = imageThumbnail;
    }
    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }
    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "AccountModel{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imageThumbnail='" + imageThumbnail + '\'' +
                ", categories='" + categories + '\'' +
                ", rate=" + rate +
                ", authorId=" + authorId +
                '}';
    }
}
