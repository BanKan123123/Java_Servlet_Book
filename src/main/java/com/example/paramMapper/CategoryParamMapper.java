package com.example.paramMapper;

import com.example.model.CategoryModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;

public class CategoryParamMapper implements IMapperParam{

    public String mapperParam (HttpServletRequest req) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String name = req.getParameter("name");
        String replaced = name.toLowerCase().replaceAll("\\s+", "-");
        String slug = replaced.replaceAll("[^a-z0-9-]", "");

        CategoryModel categoryModel = new CategoryModel(name, slug);
        return mapper.writeValueAsString(categoryModel);
    }

}
