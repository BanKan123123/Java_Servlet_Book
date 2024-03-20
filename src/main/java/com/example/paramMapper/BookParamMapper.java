package com.example.paramMapper;

import com.example.model.AuthorModel;
import com.example.model.BookModel;
import com.example.model.CategoryModel;
import com.example.service.impl.AuthorService;
import com.example.service.impl.CategoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Arrays;

public class BookParamMapper implements IMapperParam{
    private final AuthorService authorService = new AuthorService();
    private final CategoryService categoryService = new CategoryService();

    public String mapperParam(HttpServletRequest req) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String title = req.getParameter("title");
        String replaced = title.toLowerCase().replaceAll("\\s+", "-");
        String slug = replaced.replaceAll("[^a-z0-9-]", "");
        String description = req.getParameter("description");
        String imageThumbnail = req.getParameter("image-thumbnail");
        float rate = Float.parseFloat(req.getParameter("rate"));
        int liked = Integer.parseInt(req.getParameter("liked"));
        String slugAuthor = req.getParameter("author");
        AuthorModel authors = authorService.findOneAuthorBySlug(slugAuthor);
        ArrayList<CategoryModel> categories = new ArrayList<>();

        String[] categoriesSelected = req.getParameterValues("category");

        for (String categorySlug : categoriesSelected) {
            CategoryModel categoryModel = categoryService.findOneCategoryBySlug(categorySlug);
            categories.add(categoryModel);
        }
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        BookModel bookModel = new BookModel(title, slug, description, imageThumbnail, categories, rate, authors, liked, quantity);
        return mapper.writeValueAsString(bookModel);
    }

}
