package com.example.service;

import com.example.model.AuthorModel;

import java.util.List;

public interface IAuthorService {

    AuthorModel findOneAuthorBySlug(String slug);

    List<AuthorModel> findAllAuthors();

    List<AuthorModel> findAuthorsByQuery(String query);
    AuthorModel save(AuthorModel authorModel);

    void delete(String slug);

    AuthorModel update(AuthorModel authorModel, String slug);

}
