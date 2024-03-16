package com.example.model;

import java.util.ArrayList;

public class BookModel extends AbstractModel {

//    private String categories;
    private String title, slug, description, imageThumbnail;

    private ArrayList<CategoryModel> categories;
    private float rate;
    private AuthorModel author;
    private int liked, quantity;

    public BookModel() {
    }

    public BookModel(String title, String slug, String description, String imageThumbnail, float rate, AuthorModel author, int liked, int quantity) {
        this.title = title;
        this.slug = slug;
        this.description = description;
        this.imageThumbnail = imageThumbnail;
        this.rate = rate;
        this.author = author;
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

//    public String getCategories() {
//        return categories;
//    }

//    public void setCategories(String categories) {
//        this.categories = categories;
//    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
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

    public AuthorModel getAuthors() {
        return author;
    }

    public void setAuthors(AuthorModel listAuthors) {
        this.author = listAuthors;
    }

    public ArrayList<CategoryModel> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<CategoryModel> categories) {
        this.categories = categories;
    }
}
