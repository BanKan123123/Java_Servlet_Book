package com.example.service.impl;

import com.example.dao.impl.ChapterDAO;
import com.example.model.ChapterModel;
import com.example.service.IChapterService;
import com.example.utils.HttpUtil;
import com.example.utils.ResponseAPIUtils;
import com.example.wrapper.WrapperResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChapterService implements IChapterService {

    private final ChapterDAO chapterDAO = new ChapterDAO();
    private final ResponseAPIUtils<ChapterModel> responseAPIUtils = new ResponseAPIUtils<>();


    @Override
    public ChapterModel findOneChapterBySlug(String slug) {
        return chapterDAO.findOneChapter(slug);
    }

    @Override
    public List<ChapterModel> findAllChapter() {
        return chapterDAO.findAllChapter();
    }

    @Override
    public List<ChapterModel> findChapterByQuery(String query) {
        return chapterDAO.findChapterByQuery(query);
    }

    @Override
    public List<ChapterModel> findChapterByBookSlug(String slug) {
        return chapterDAO.findChapterByBookSlug(slug);
    }

    @Override
    public ChapterModel save(ChapterModel chapterModel) {
        chapterDAO.addChapter(chapterModel);
        return findOneChapterBySlug(chapterModel.getSlug());
    }

    @Override
    public void delete(String slug) {
        chapterDAO.deleteChapter(slug);
    }

    @Override
    public ChapterModel update(ChapterModel chapterModel, String slug) {
        chapterDAO.updateChapter(chapterModel, slug);
        return findOneChapterBySlug(chapterModel.getSlug());
    }

    public void findData(String pathInfo, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        WrapperResponse<ChapterModel> wrapperResponse = new WrapperResponse<>();

        if (pathInfo == null || pathInfo.equals("/")) {
            ArrayList<ChapterModel> listBooks = (ArrayList<ChapterModel>) findAllChapter();
            responseAPIUtils.getDataSuccess(wrapperResponse, listBooks, resp);
        } else {
            String slug = pathInfo.substring(1);
            if (slug.equals("search")) {
                String query = req.getParameter("query");
                if (query != null) {
                    ArrayList<ChapterModel> listBooks = (ArrayList<ChapterModel>) findChapterByQuery(query);
                    responseAPIUtils.getDataSuccess(wrapperResponse, listBooks, resp);
                } else {
                    responseAPIUtils.notFoundAPI(wrapperResponse, resp);
                }
            } else {
                ArrayList<ChapterModel> chapters = (ArrayList<ChapterModel>) findChapterByBookSlug(slug);
                if (chapters != null) {
                    responseAPIUtils.getDataSuccess(wrapperResponse, chapters, resp);
                } else {
                    responseAPIUtils.notFoundAPI(wrapperResponse, resp);
                }
            }

        }
        mapper.writeValue(resp.getOutputStream(), wrapperResponse);
    }

    public void insertData(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8"); // Để khi mà thằng client nó có chuỗi tiếng việt
        resp.setContentType("application/json"); // Trả kết quả cho thằng client, và cụ thể là trả về json, thì thằng server định nghĩa một header để thằng Client hiểu.
        WrapperResponse<ChapterModel> wrapperResponse = new WrapperResponse<>();
        ChapterModel chapterModel = HttpUtil.of(req.getReader()).toModel(ChapterModel.class);

        if (chapterModel.getTitle() == null || chapterModel.getTitle().isEmpty() || chapterModel.getSlug() == null || chapterModel.getSlug().isEmpty() || chapterModel.getData() == null || chapterModel.getData().isEmpty() || chapterModel.getChapterIndex() == 0) {
            responseAPIUtils.requiredDataAPI(wrapperResponse, resp);
        } else {
            ChapterModel findChapter = findOneChapterBySlug(chapterModel.getSlug());
            if (findChapter == null) {
                ChapterModel chapterModel1 = save(chapterModel);
                ArrayList<ChapterModel> chapters = new ArrayList<>();
                chapters.add(chapterModel1);
                responseAPIUtils.insertSuccess(wrapperResponse, chapters, resp);
            } else {
                responseAPIUtils.duplicateDataAPI(wrapperResponse, resp);
            }
        }
        mapper.writeValue(resp.getOutputStream(), wrapperResponse);
    }

    public void update(String pathInfo, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        WrapperResponse<ChapterModel> wrapperResponse = new WrapperResponse<>();
        ChapterModel chapterModel = HttpUtil.of(req.getReader()).toModel(ChapterModel.class);

        if (pathInfo != null && !pathInfo.isEmpty()) {
            String[] path = pathInfo.split("/");
            if (path.length == 3) {
                if (path[1].equals("update")) {
                    if (chapterModel.getTitle() == null || chapterModel.getTitle().isEmpty() || chapterModel.getSlug() == null || chapterModel.getSlug().isEmpty() || chapterModel.getData() == null || chapterModel.getData().isEmpty() || chapterModel.getChapterIndex() == 0) {
                        responseAPIUtils.requiredDataAPI(wrapperResponse, resp);
                    } else {
                        ArrayList<ChapterModel> chapters = new ArrayList<>();
                        String slug = path[2];
                        if (findOneChapterBySlug(slug) != null) {
                            if (findOneChapterBySlug(chapterModel.getSlug()) == null) {
                                ChapterModel chapterModel1 = update(chapterModel, slug);
                                chapters.add(chapterModel1);
                                responseAPIUtils.updateSuccess(wrapperResponse, chapters, resp);
                            } else {
                                responseAPIUtils.duplicateDataAPI(wrapperResponse, resp);
                            }
                        } else {
                            responseAPIUtils.notFoundAPI(wrapperResponse, resp);
                        }
                    }
                } else {
                    responseAPIUtils.ServerError(wrapperResponse, resp);
                }
            } else {
                responseAPIUtils.ServerError(wrapperResponse, resp);
            }
        } else {
            responseAPIUtils.ServerError(wrapperResponse, resp);
        }

        mapper.writeValue(resp.getOutputStream(), wrapperResponse);
    }

    public void delete(String pathInfo, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        WrapperResponse<ChapterModel> wrapperResponse = new WrapperResponse<>();
        if (pathInfo != null && !pathInfo.isEmpty()) {
            String[] path = pathInfo.split("/");
            if (path.length == 3) {
                if (path[1].equals("delete")) {
                    String slug = path[2];
                    if (findOneChapterBySlug(slug) != null) {
                        delete(slug);
                        responseAPIUtils.deleteSuccess(wrapperResponse, resp);
                    } else {
                        responseAPIUtils.notFoundAPI(wrapperResponse, resp);
                    }
                } else {
                    responseAPIUtils.ServerError(wrapperResponse, resp);
                }
            } else {
                responseAPIUtils.ServerError(wrapperResponse, resp);
            }
        } else {
            responseAPIUtils.ServerError(wrapperResponse, resp);
        }
        mapper.writeValue(resp.getOutputStream(), wrapperResponse);
    }
}
