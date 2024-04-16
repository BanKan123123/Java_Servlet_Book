package com.example.dao.impl;

import com.example.dao.IBookDAO;
import com.example.mapper.BookMapper;
import com.example.model.BookModel;
import com.example.model.CategoryModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookDAO extends AbstractDAO<BookModel> implements IBookDAO {
    @Override
    public List<BookModel> findAllBooks() {
        String sql = "SELECT DISTINCT * FROM books, author, category ,categoriesonbook WHERE books.id = categoriesonbook.bookId AND category.id = categoriesonbook.categoryId AND books.authorId = author.id ORDER BY books.title ASC";
        List<BookModel> bookModels = query(sql, new BookMapper());
        bookModels.removeIf(Objects::isNull);
        return bookModels;
    }

    @Override
    public BookModel findOneBookById(int id) {
        String sql = "SELECT DISTINCT * FROM books, author, category ,categoriesonbook WHERE books.id = categoriesonbook.bookId AND category.id = categoriesonbook.categoryId AND books.authorId = author.id AND books.id = ?";
        return query(sql, new BookMapper(), id).get(0);
    }

    @Override
    public List<BookModel> findBookByQuery(String query) {
        String sql = "SELECT DISTINCT * FROM books, author, category ,categoriesonbook WHERE books.id = categoriesonbook.bookId AND category.id = categoriesonbook.categoryId AND books.authorId = author.id AND title LIKE '%" + query + "%' ORDER BY title ASC";
        List<BookModel> bookModels = query(sql, new BookMapper());
        if (bookModels == null) {
            return null;
        } else {
            bookModels.removeIf(Objects::isNull);
        }
        return bookModels;
    }

    @Override
    public BookModel findOneBookBySlug(String slug) {
        String sql = "SELECT DISTINCT * FROM books, author, category ,categoriesonbook WHERE books.id = categoriesonbook.bookId AND category.id = categoriesonbook.categoryId AND books.authorId = author.id AND books.slug = ?";
        if (query(sql, new BookMapper(), slug) == null || query(sql, new BookMapper(), slug).isEmpty()) {
            return null;
        }
        return query(sql, new BookMapper(), slug).get(0);
    }

    @Override
    public Long addBook(BookModel bookModel) {
        String sql = "INSERT INTO books (title, slug , description, imageThumbnail, rate, liked, authorId, quantity) VALUES (?, ? ,? ,? ,? ,?, ? , ?)";
        return insert(sql, bookModel.getTitle(), bookModel.getSlug(), bookModel.getDescription(), bookModel.getImageThumbnail(), bookModel.getRate(), bookModel.getLiked(), bookModel.getAuthors().getId(), bookModel.getQuantity());
    }

    public void addCategoriesOnBook(ArrayList<CategoryModel> categoryModels, long idBook) {
        String sqlCategoriesOnBook = "INSERT INTO categoriesonbook (categoryId, bookId) VALUES (? , ?)";
        categoryModels.forEach(categoryModel -> insert(sqlCategoriesOnBook, categoryModel.getId(), idBook));
    }

    @Override
    public void updateBook(BookModel bookModel, String slug) {
        String sql = "UPDATE books SET `title` = ?, `slug` = ?, `description` = ?, `imageThumbnail` = ?, `rate` = ?, `liked` = ? ,`authorId` = ?, `quantity` = ? WHERE `slug` = ?";
        update(sql, bookModel.getTitle(), bookModel.getSlug(), bookModel.getDescription(), bookModel.getImageThumbnail(), bookModel.getRate(), bookModel.getLiked(), bookModel.getAuthors().getId(), bookModel.getQuantity(), slug);
    }

    public void deleteCategoriesOnBook(BookModel bookModel) {
        String sqlDeleteCategoriesOnBook = "DELETE FROM categoriesonbook WHERE bookId = ?";
        update(sqlDeleteCategoriesOnBook, bookModel.getId());
    }

    @Override
    public void deleteBook(String slug) {
        String sql = "DELETE FROM books WHERE slug = ?";
        update(sql, slug);
    }

}
