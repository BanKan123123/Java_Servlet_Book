package com.example.utils;

import com.example.wrapper.WrapperResponse;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class ResponseAPIUtils<T> {

    public void ServerError(WrapperResponse<T> response, HttpServletResponse res) {
        response.setStatus(500);
        response.setMessage("Server error internal");
        res.setStatus(500);
    }

    public void getDataSuccess(WrapperResponse<T> response, ArrayList<T> data, HttpServletResponse res) {
        response.setStatus(200);
        response.setMessage("Find all success");
        response.setData(data);
        res.setStatus(200); // created
    }

    public void insertSuccess(WrapperResponse<T> response, ArrayList<T> data, HttpServletResponse res) {
        response.setStatus(201);
        response.setMessage("Insert success");
        response.setData(data);
        res.setStatus(201);
    }

    public void updateSuccess(WrapperResponse<T> response, ArrayList<T> data, HttpServletResponse res) {
        response.setStatus(200);
        response.setMessage("Update success");
        response.setData(data);
        res.setStatus(200);
    }

    public void deleteSuccess(WrapperResponse<T> response, HttpServletResponse res) {
        response.setStatus(200); // No content response
        response.setMessage("Delete success");
        res.setStatus(200);
    }

    public void notFoundAPI(WrapperResponse<T> response, HttpServletResponse res) {
        response.setStatus(404);
        response.setMessage("Not found data from param");
        res.setStatus(404);
    }

    public void requiredDataAPI(WrapperResponse<T> response, HttpServletResponse res) {
        response.setStatus(400);
        response.setMessage("You have to fill full the field required");
        res.setStatus(400);
    }

    public void duplicateDataAPI(WrapperResponse<T> response, HttpServletResponse res) {
        response.setStatus(409); //Conflict DB
        response.setMessage("Your data is duplicate in database");
        res.setStatus(409);
    }
}
