package com.example.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;

public class HttpUtil {
    private final String value;
    public HttpUtil(String value) {
        this.value = value;
    }

    public <T> T toModel(Class<T> tClass) throws JsonProcessingException {
        return new ObjectMapper().readValue(value, tClass);
    }
    public static HttpUtil of(BufferedReader br) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        while((line = br.readLine()) != null) {
            sb.append(line);
        }
        return new HttpUtil(sb.toString());
    }
}
