package com.example.service.impl;

import com.example.dao.impl.ChapterDAO;
import com.example.model.BookModel;
import com.example.model.ChapterModel;
import com.example.service.IChapterService;
import com.example.utils.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChapterService implements IChapterService {

    private final ChapterDAO chapterDAO = new ChapterDAO();

    @Override
    public ChapterModel findOneChapterBySlug(String slug) {
        return chapterDAO.findOneChapter(slug);
    }

    @Override
    public List<ChapterModel> findAllChapter() {
        return chapterDAO.findAllChapter();
    }

    @Override
    public ChapterModel save(ChapterModel chapterModel) {
        chapterDAO.addChapter(chapterModel);
        return findOneChapterBySlug(chapterModel.getSlug());
    }

    @Override
    public void delete(int id) {
        chapterDAO.deleteChapter(id);
    }

    @Override
    public ChapterModel update(ChapterModel chapterModel, int id) {
        chapterDAO.updateChapter(chapterModel, id);
        return findOneChapterBySlug(chapterModel.getSlug());
    }

    public void findData(String pathInfo, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        if (pathInfo == null || pathInfo.equals("/")) {
            ArrayList<ChapterModel> listBooks = (ArrayList<ChapterModel>) findAllChapter();
            mapper.writeValue(resp.getOutputStream(), listBooks);
        } else {
            String slug = pathInfo.substring(1);
            ChapterModel chapterModel = findOneChapterBySlug(slug);
            if (chapterModel != null) {
                mapper.writeValue(resp.getOutputStream(), chapterModel);
            } else {
                resp.setStatus(404);
            }
        }
    }

    public void insertData(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8"); // Để khi mà thằng client nó có chuỗi tiếng việt
        resp.setContentType("application/json"); // Trả kết quả cho thằng client, và cụ thể là trả về json, thì thằng server định nghĩa một header để thằng Client hiểu.
        ChapterModel chapterModel = HttpUtil.of(req.getReader()).toModel(ChapterModel.class);
        ChapterModel chapterModel1 = save(chapterModel);
        mapper.writeValue(resp.getOutputStream(), chapterModel1);
    }

    public void update(String pathInfo, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        ChapterModel chapterModel = HttpUtil.of(req.getReader()).toModel(ChapterModel.class);

        if (pathInfo != null && !pathInfo.isEmpty()) {
            String[] path = pathInfo.split("/");
            if (path.length == 3) {
                if (path[1].equals("update")) {
                    int id = Integer.parseInt(path[2]);
                    ChapterModel chapterModelRes = update(chapterModel, id);
                    mapper.writeValue(resp.getOutputStream(), chapterModelRes);
                }
            }
        }
    }

    public void delete(String pathInfo, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        if (pathInfo != null && !pathInfo.isEmpty()) {
            String[] path = pathInfo.split("/");
            if (path.length == 3) {
                if (path[1].equals("delete")) {
                    int id = Integer.parseInt(path[2]);
                    delete(id);
                }
            }
        }
    }
}