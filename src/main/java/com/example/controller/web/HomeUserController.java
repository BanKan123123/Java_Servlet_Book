package com.example.controller.web;

import com.example.apihandler.GenericHandleAPI;
import com.example.model.*;
import com.example.wrapper.WrapperResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/user-home"})
public class HomeUserController extends HttpServlet {

    private final GenericHandleAPI<BookModel> handleAPIBook = new GenericHandleAPI<>();
    private final GenericHandleAPI<CategoryModel> handleAPICategory = new GenericHandleAPI<>();

    private final GenericHandleAPI<ChapterModel> handleAPIChapter = new GenericHandleAPI<>();

    String urlAPIBook, urlAPICategory, urlAPIChapter;

    @Override
    public void init() {
        urlAPIBook = "http://localhost:8080/demo2-1.0-SNAPSHOT/api-admin-books";
        urlAPICategory = "http://localhost:8080/demo2-1.0-SNAPSHOT/api-admin-category";
        urlAPIChapter = "http://localhost:8080/demo2-1.0-SNAPSHOT/api-admin-chapters";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        String path = "/views/web/homeuser.jsp";

        WrapperResponse<CategoryModel> responseDataCategory = handleAPICategory.getMultipleAPIHandle(urlAPICategory, pathInfo);
        WrapperResponse<BookModel> responseDataBook = null;

        if (req.getParameter("search") != null) {
            String pathSearch = urlAPIBook + "/search?query=" + req.getParameter("search");
            responseDataBook = handleAPIBook.getMultipleAPIHandle(pathSearch, pathInfo);
        } else if (req.getParameter("slug") != null) {
            ObjectMapper mapper = new ObjectMapper();
            String pathDetail = "/views/web/home-user-bookdetail.jsp";
            String urlDetail = urlAPIBook + "/" + req.getParameter("slug");
            String urlChapterByBookSlug = urlAPIChapter + "/" + req.getParameter("slug");
            String urlRangeBookByMonth = urlAPIBook + "/range";
            WrapperResponse<BookModel> responseDataBookDetail = handleAPIBook.getMultipleAPIHandle(urlDetail, pathInfo);
            WrapperResponse<ChapterModel> responseDataChapterByBook = handleAPIChapter.getMultipleAPIHandle(urlChapterByBookSlug, pathInfo);
            WrapperResponse<BookModel> responseDataBookRange = handleAPIBook.getMultipleAPIHandle(urlRangeBookByMonth, pathInfo);
            req.setAttribute("responseBookDetail", responseDataBookDetail.getData().get(0));
            req.setAttribute("responseChapterByBook", responseDataChapterByBook.getData());
            req.setAttribute("responseBookRange", responseDataBookRange.getData());

            req.getRequestDispatcher(pathDetail).forward(req, resp);
        } else {
            responseDataBook = handleAPIBook.getMultipleAPIHandle(urlAPIBook, pathInfo);
        }

        if (responseDataBook != null) {
            req.setAttribute("responseBook", responseDataBook.getData());
        }
        req.setAttribute("responseCategory", responseDataCategory.getData());
        req.getRequestDispatcher(path).forward(req, resp);
    }
}
