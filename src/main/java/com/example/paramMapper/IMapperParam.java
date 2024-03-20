package com.example.paramMapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;

public interface IMapperParam {
    String mapperParam(HttpServletRequest req) throws JsonProcessingException;
}
