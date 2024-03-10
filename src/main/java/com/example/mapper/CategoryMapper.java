package com.example.mapper;

import com.example.model.CategoryModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper implements RowMapper<CategoryModel>{
    @Override
    public CategoryModel mapRow(ResultSet rs) {

        try {
            CategoryModel categoryModel = new CategoryModel();
            categoryModel.setId(rs.getInt("id"));
            categoryModel.setName(rs.getString("name"));
            categoryModel.setSlug(rs.getString("slug"));
            categoryModel.setCreated_at(rs.getTimestamp("created_at"));
            categoryModel.setUpdated_at(rs.getTimestamp("updated_at"));

            return categoryModel;
        }catch(SQLException ex){
            return null;
        }

    }
}
