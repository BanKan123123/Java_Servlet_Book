package com.example.paramMapper;

import com.example.model.BookModel;
import com.example.model.ChapterModel;
import com.example.service.impl.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;

public class ChapterParamMapper implements IMapperParam {
    private final BookService bookService = new BookService();

    public String mapperParam(HttpServletRequest req) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String title = req.getParameter("title");
        String replaced = title.toLowerCase().replaceAll("\\s+", "-");
        String slug = replaced.replaceAll("[^a-z0-9-]", "");

        String data = req.getParameter("data");
        String slugBook = req.getParameter("slug-book");
        BookModel bookModel = bookService.findOneBookBySlug(slugBook);
        int chapterIndex = Integer.parseInt(req.getParameter("chapter-index"));
        String audioUrl = req.getParameter("audio-url");
        String summary = req.getParameter("summary");

        ChapterModel chapterModel = new ChapterModel(bookModel, chapterIndex, title, slug, data, audioUrl, summary);

        return mapper.writeValueAsString(chapterModel);
    }
}
