package com.example.controller.web;

import com.example.apihandler.GenericHandleAPI;
import com.example.model.AccountModel;
import com.example.service.impl.AccountService;
import com.example.utils.FormUtils;
import com.example.wrapper.WrapperResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
    private final AccountService accountService = new AccountService();

    private String message;

    String urlAPIAccount = "http://localhost:8080/demo2-1.0-SNAPSHOT/api-admin-account/login";
    GenericHandleAPI<AccountModel> handleAPIAccount = new GenericHandleAPI<>();

    public void init() {
        message = "Username or password is invalid";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action != null && action.equals("login")) {
            String status = req.getParameter("status");
            if (status != null && status.equals("false")) {
                req.setAttribute("message", message);
                req.setAttribute("alert", "danger");
            }
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/web/login.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/web/home.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        if (action != null && action.equals("login")) {
            AccountModel accountModel = FormUtils.toModel(AccountModel.class, req);
            Gson gson = new Gson();
            String json = gson.toJson(accountModel);

            URL url = new URL(urlAPIAccount);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setDoOutput(true);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = json.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
                os.flush();
            }

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder responseBuilder = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    responseBuilder.append(responseLine.trim());
                }

                if (responseBuilder.length() > 0) { // Kiểm tra xem có dữ liệu trả về không
                    WrapperResponse<AccountModel> wrapperResponse = mapper.readValue(responseBuilder.toString(), new TypeReference<>() {
                        @Override
                        public Type getType() {
                            return new TypeToken<WrapperResponse<AccountModel>>() {
                            }.getType();
                        }
                    });
                    if (!wrapperResponse.getData().isEmpty()) {
                        AccountModel accountModel1 = wrapperResponse.getData().get(0);
                        session.setAttribute("userRole", accountModel1.getRole());
                        session.setAttribute("username", accountModel1.getUsername());
                        if (accountModel1.getRole() == 1) {
                            resp.sendRedirect(req.getContextPath() + "/book");
                        } else {
                            resp.sendRedirect(req.getContextPath() + "/user-home");
                        }
                    } else {
                        resp.sendRedirect(req.getContextPath() + "/login?action=login&status=false");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
