package com.example.mapper;

import com.example.model.AuthorModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorMapper implements RowMapper<AuthorModel> {

    @Override
    public AuthorModel mapRow(ResultSet rs) {
        try {
            AuthorModel authorModel = new AuthorModel();
            authorModel.setId(rs.getInt("id"));
            authorModel.setName(rs.getString("name"));
            authorModel.setSlug(rs.getString("slug"));
            authorModel.setCreated_at(rs.getTimestamp("created_at"));
            authorModel.setUpdated_at(rs.getTimestamp("updated_at"));
            return authorModel;
        } catch (SQLException ex) {
            return null;
        }
    }
}
