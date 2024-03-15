package com.example.apihandler;

import com.example.wrapper.WrapperResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

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

    public void postAPIHandle (String urlAPI, String jsonBook, HttpServletResponse resp, ObjectMapper mapper) throws IOException {
        URL url = new URL(urlAPI);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-type", "application/json");
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonBook.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
            os.flush(); // Flush OutputStream để chắc chắn rằng dữ liệu được gửi đi
        } catch (IOException e) {
            // Xử lý exception khi gửi yêu cầu
            mapper.writeValue(resp.getOutputStream(), "Error occurred while sending request");
            return;
        }

        int responseCode = connection.getResponseCode();
        if (responseCode == 201) {
            // Đọc phản hồi từ API (nếu cần)
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                StringBuffer responseBuffer = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    responseBuffer.append(inputLine);
                }
                String apiResponse = responseBuffer.toString();
                mapper.writeValue(resp.getOutputStream(), apiResponse);
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
