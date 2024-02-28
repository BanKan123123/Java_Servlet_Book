package com.example.service.impl;

import com.example.dao.impl.ChapterDAO;
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
    public ChapterModel findOneChapterById(int id) {
        return chapterDAO.findOneChapter(id);
    }

    @Override
    public List<ChapterModel> findAllChapter() {
        return chapterDAO.findAllChapter();
    }

    @Override
    public ChapterModel save(ChapterModel chapterModel) {
        chapterDAO.addChapter(chapterModel);
        return chapterModel;
    }

    @Override
    public ChapterModel delete(int id) {
        chapterDAO.deleteChapter(id);
        return this.findOneChapterById(id);
    }

    @Override
    public ChapterModel update(ChapterModel chapterModel, int id) {
        chapterDAO.updateChapter(chapterModel, id);
        return chapterModel;
    }

    public void findData(String pathInfo, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        if (pathInfo == null || pathInfo.equals("/")) {
            ArrayList<ChapterModel> listBooks = (ArrayList<ChapterModel>) findAllChapter();
            mapper.writeValue(resp.getOutputStream(), listBooks);
        } else {
            String idString = pathInfo.substring(1);
            int id = Integer.parseInt(idString);
            ChapterModel chapterModel = findOneChapterById(id);
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
            String requestString = pathInfo.substring(1);
            if (requestString.equals("update")) {
                String idString = pathInfo.substring(2);
                int id = Integer.parseInt(idString);
                ChapterModel chapterModelRes = update(chapterModel, id);
                mapper.writeValue(resp.getOutputStream(), chapterModelRes);
            }
        }
    }

    public void delete(String pathInfo, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        if (pathInfo != null && !pathInfo.isEmpty()) {
            String idString = pathInfo.substring(1);
            int id = Integer.parseInt(idString);
            ChapterModel chapterModel = delete(id);
            mapper.writeValue(resp.getOutputStream(), chapterModel);
        }
    }
}
