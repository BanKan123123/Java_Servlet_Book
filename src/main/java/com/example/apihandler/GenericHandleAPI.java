package com.example.apihandler;

import com.example.model.BookModel;
import com.example.wrapper.WrapperResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GenericHandleAPI<T> {
    public void getAPIHandle(String urlAPI, String pathInfo, String path, HttpServletResponse resp, HttpServletRequest req) throws IOException, ServletException {
        ObjectMapper mapper = new ObjectMapper();
        if (pathInfo == null || pathInfo.equals("/")) {
            urlAPI = urlAPI;
        } else {
            urlAPI = urlAPI + "/" + pathInfo.substring(1);
        }

        URL url = new URL(urlAPI);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder responseBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            responseBuilder.append(line);
        }
        reader.close();
        WrapperResponse<T> wrapperResponse = mapper.readValue(responseBuilder.toString(), WrapperResponse.class);
        req.setAttribute("responseData", wrapperResponse.getData());
        req.getRequestDispatcher(path).forward(req, resp);
        connection.disconnect();
    }
}
