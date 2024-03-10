package com.example.model;

import java.sql.Timestamp;

public class BookModel extends AbstractModel {
    private String title, slug, description, imageThumbnail, categories;
    private float rate;
    private int authorId, liked, quantity;

    public BookModel() {

    }

    public BookModel(int id, Timestamp created_at, Timestamp updated_at, String title, String slug, String description, String imageThumbnail, String categories, float rate, int authorId, int liked, int quantity) {
        super(id, created_at, updated_at);
        this.title = title;
        this.slug = slug;
        this.description = description;
        this.imageThumbnail = imageThumbnail;
        this.categories = categories;
        this.rate = rate;
        this.authorId = authorId;
        this.liked = liked;
        this.quantity = quantity;
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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public int getLiked() {
        return liked;
    }

    public void setLiked(int liked) {
        this.liked = liked;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
