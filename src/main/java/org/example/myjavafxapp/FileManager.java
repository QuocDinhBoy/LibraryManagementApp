package org.example.myjavafxapp;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static final String DOCUMENT_FILE = "documents.txt";
    private static final String USER_FILE = "users.txt";

    // Ghi danh sách tài liệu vào file
    public static void saveDocuments(List<Document> documents) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DOCUMENT_FILE))) {
            for (Document document : documents) {
                writer.write(document.getId() + "," + document.getName() + "," + document.getAuthor() + "," + document.getQuantity());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<Document> loadDocuments() {
        List<Document> documents = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(DOCUMENT_FILE))) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String id = parts[0];
                String name = parts[1];
                String author = parts[2];
                int quantity = Integer.parseInt(parts[3]);
                documents.add(new Document(id, name, author, quantity));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return documents;
    }
}
