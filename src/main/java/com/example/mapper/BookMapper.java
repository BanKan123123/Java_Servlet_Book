package com.example.mapper;

import com.example.model.AuthorModel;
import com.example.model.BookModel;
import com.example.model.CategoryModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class BookMapper implements RowMapper<BookModel> {
    private final Set<Integer> processedBookIds = new HashSet<>();
    private final Map<Integer, BookModel> booksById = new HashMap<>();

    @Override
    public BookModel mapRow(ResultSet resultSet) {
        try {
            int bookId = (int) resultSet.getLong(1);
            ArrayList<CategoryModel> categories = new ArrayList<>();
            BookModel bookModel = new BookModel();
            AuthorModel authorModel = new AuthorModel();

            CategoryModel categoryModel = new CategoryModel();
            categoryModel.setId(resultSet.getInt(17));
            categoryModel.setName(resultSet.getString(18));
            categoryModel.setSlug(resultSet.getString(19));
            categoryModel.setCreated_at(resultSet.getTimestamp(20));
            categoryModel.setUpdated_at(resultSet.getTimestamp(21));

            if (processedBookIds.contains(bookId)) {
                // Nếu bookId đã được xử lý trước đó, chỉ cần thêm danh mục mới vào danh sách hiện tại của book
                booksById.get(bookId).getCategories().add(categoryModel);
                return null;
            } else {
                // Nếu bookId chưa được xử lý, thêm nó vào danh sách đã xử lý
                processedBookIds.add(bookId);

                bookModel.setId(bookId);
                bookModel.setTitle(resultSet.getString(2));
                bookModel.setSlug(resultSet.getString(3));
                bookModel.setDescription(resultSet.getString(4));
                bookModel.setImageThumbnail(resultSet.getString(5));
                bookModel.setRate(resultSet.getFloat(6));
                bookModel.setLiked(resultSet.getInt(7));
                bookModel.setQuantity(resultSet.getInt(9));
                bookModel.setCreated_at(resultSet.getTimestamp(10));
                bookModel.setUpdated_at(resultSet.getTimestamp(11));

                authorModel.setId(resultSet.getInt(12));
                authorModel.setName(resultSet.getString(13));
                authorModel.setSlug(resultSet.getString(14));
                authorModel.setCreated_at(resultSet.getTimestamp(15));
                authorModel.setUpdated_at(resultSet.getTimestamp(16));
                bookModel.setAuthors(authorModel);

                booksById.put(bookId, bookModel);
                categories.add(categoryModel);
                bookModel.setCategories(categories);
            }
            return bookModel;
        } catch (SQLException e) {
            return null;
        }
    }
}
