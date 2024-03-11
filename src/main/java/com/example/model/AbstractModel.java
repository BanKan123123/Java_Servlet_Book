package com.example.model;

import java.sql.Timestamp;

public class AbstractModel {
        private int id;
        private Timestamp created_at;
        private Timestamp updated_at;

    public AbstractModel() {
    }

    public AbstractModel(int id, Timestamp created_at, Timestamp updated_at) {
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

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }
}
