package com.example.dao;

import com.example.model.AuthorModel;
import com.example.model.BookModel;

import java.util.List;

public interface IAuthorDAO extends GenericDAO<AuthorModel>{

    List<AuthorModel> findAllAuthors();
    AuthorModel findAuthorBySlug(String slug);

    Long addAuthor(AuthorModel authorModel);

    void updateAuthor(AuthorModel authorModel, String slug);

    void deleteAuthor(String slug);


}
