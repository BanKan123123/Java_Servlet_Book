package com.example.apihandler;

import com.example.wrapper.WrapperResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class GenericHandleAPI<T> {
//    public void getAPIHandle(String urlAPI, String pathInfo, String path, String attribute, HttpServletResponse resp, HttpServletRequest req) throws IOException, ServletException {
//        ObjectMapper mapper = new ObjectMapper();
//        if (pathInfo == null || pathInfo.equals("/")) {
//            urlAPI = urlAPI;
//        } else {
//            urlAPI = urlAPI + "/" + pathInfo.substring(1);
//        }
//
//        URL url = new URL(urlAPI);
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setRequestMethod("GET");
//
//        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//        StringBuilder responseBuilder = new StringBuilder();
//        String line;
//        while ((line = reader.readLine()) != null) {
//            responseBuilder.append(line);
//        }
//        reader.close();
//        WrapperResponse<T> wrapperResponse = mapper.readValue(responseBuilder.toString(), WrapperResponse.class);
//        req.setAttribute(attribute, wrapperResponse.getData());
//        req.getRequestDispatcher(path).forward(req, resp);
//        connection.disconnect();
//    }

    public WrapperResponse<T> getMultipleAPIHandle(String urlAPI, String pathInfo) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        if (pathInfo == null || pathInfo.equals("/")) {
            urlAPI = urlAPI;
        } else {
            urlAPI = urlAPI + "/" + pathInfo;
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
        connection.disconnect();
        return wrapperResponse;
    }

    public void postAPIHandle(String urlAPI, String jsonResponse, HttpServletResponse resp, ObjectMapper mapper) throws IOException {
        URL url = new URL(urlAPI);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-type", "application/json");
        connection.setDoOutput(true);
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonResponse.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
            os.flush(); // Flush OutputStream để chắc chắn rằng dữ liệu được gửi đi
        } catch (IOException e) {
            mapper.writeValue(resp.getOutputStream(), "Error occurred while sending request");
        }
        handleResponseAPI(connection, resp, mapper, 201);

//        handleConnection((HttpURLConnection) url.openConnection(), jsonResponse, resp, mapper, 201);
    }

    public void putAPIHandel(String urlAPI, String jsonResponse, HttpServletResponse resp, ObjectMapper mapper) throws IOException {
        URL url = new URL(urlAPI);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-type", "application/json");
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonResponse.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
            os.flush(); // Flush OutputStream để chắc chắn rằng dữ liệu được gửi đi
        } catch (IOException e) {
            // Xử lý exception khi gửi yêu cầu
            mapper.writeValue(resp.getOutputStream(), "Error occurred while sending request");
        }
        handleResponseAPI(connection, resp, mapper, 200);
//        handleConnection(connection, jsonResponse, resp, mapper, 200);
    }

    public void deleteAPIHandel(String urlAPI, HttpServletResponse resp, ObjectMapper mapper) throws IOException {
        URL url = new URL(urlAPI);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");

        handleResponseAPI(connection, resp, mapper, 200);
    }

//    public void handleConnection(HttpURLConnection connection, String jsonResponse, HttpServletResponse resp, ObjectMapper mapper, int status) throws IOException {
//        try (OutputStream os = connection.getOutputStream()) {
//            byte[] input = jsonResponse.getBytes(StandardCharsets.UTF_8);
//            os.write(input, 0, input.length);
//            os.flush(); // Flush OutputStream để chắc chắn rằng dữ liệu được gửi đi
//        } catch (IOException e) {
//            // Xử lý exception khi gửi yêu cầu
//            mapper.writeValue(resp.getOutputStream(), "Error occurred while sending request");
//        }
//
//        handleResponseAPI(connection, resp, mapper, status);
//    }

    public void handleResponseAPI(HttpURLConnection connection, HttpServletResponse resp, ObjectMapper mapper, int status) throws IOException {
        int responseCode = connection.getResponseCode();
        if (responseCode == status) {
            // Đọc phản hồi từ API (nếu cần)
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                StringBuffer responseBuffer = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    responseBuffer.append(inputLine);
                }
//                String apiResponse = responseBuffer.toString();
//                mapper.writeValue(resp.getOutputStream(), apiResponse);
            } catch (IOException e) {
                // Xử lý exception khi đọc phản hồi từ API
                mapper.writeValue(resp.getOutputStream(), "Error occurred while reading response from API");
            } finally {
                connection.disconnect();
            }
        } else {
            // Xử lý lỗi khi gửi yêu cầu đến API
            mapper.writeValue(resp.getOutputStream(), "Connection error, HTTP response code: " + responseCode);
        }
    }
}
