package com.example.wrapper;

import java.util.ArrayList;

public class WrapperResponse<T> {

    private int status;

    private String message;

    private ArrayList<T> data;

    public WrapperResponse() {
    }

    public WrapperResponse(int status, String message, ArrayList<T> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<T> getData() {
        return data;
    }

    public void setData(ArrayList<T> data) {
        this.data = data;
    }
}
