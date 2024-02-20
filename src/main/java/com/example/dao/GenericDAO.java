package com.example.dao;

import javax.swing.tree.RowMapper;
import java.util.List;

public interface GenericDAO<T> {
    <T> List<T> query(String sql, RowMapper rowMapper, Object... parameters);
    void update (String sql, Object... parameters);
    void insert (String sql, Object... parameters);
}
