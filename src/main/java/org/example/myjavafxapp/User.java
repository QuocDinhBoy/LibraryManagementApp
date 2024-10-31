package org.example.myjavafxapp;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String userId;
    private String name;
    private List<Document> borrowedDocuments;

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
        this.borrowedDocuments = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
}
