package com.example.model;

import java.util.Date;

public class AbstractModel {

    private int id;
    private Date created_at;
    private Date updated_at;

    public AbstractModel() {
    }

    public AbstractModel(int id, Date created_at, Date updated_at) {
        this.id = id;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
}
