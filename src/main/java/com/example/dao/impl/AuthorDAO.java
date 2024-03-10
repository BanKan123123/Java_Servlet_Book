package com.example.dao.impl;

import com.example.dao.IAuthorDAO;
import com.example.mapper.AuthorMapper;
import com.example.model.AuthorModel;

import java.util.List;

public class AuthorDAO extends AbstractDAO<AuthorModel> implements IAuthorDAO {
    @Override
    public List<AuthorModel> findAllAuthors() {
        String sql = "SELECT * FROM author ORDER BY name ASC";
        return query(sql, new AuthorMapper());
    }

    @Override
    public AuthorModel findAuthorBySlug(String slug) {
        String sql = "SELECT * FROM author WHERE slug = ?";
        return query(sql, new AuthorMapper(), slug).get(0);
    }

    @Override
    public Long addAuthor(AuthorModel authorModel) {
        String sql = "INSERT INTO author (name, slug) VALUES (? , ?)";
        return insert(sql, authorModel.getName(), authorModel.getSlug());
    }

    @Override
    public void updateAuthor(AuthorModel authorModel, String slug) {
        String sql = "UPDATE author SET name = ?, slug = ? WHERE slug = ?";
        update(sql, authorModel.getName(), authorModel.getSlug(), slug);
    }

    @Override
    public void deleteAuthor(String slug) {
        String sql = "DELETE FROM author WHERE slug = ?";
        update(sql, slug);
    }
}
