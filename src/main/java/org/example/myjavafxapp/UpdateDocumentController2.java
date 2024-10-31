package org.example.myjavafxapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class UpdateDocumentController2 {

    private Stage stage;
    private Scene scene;
    private Parent root;
    List<Document> Documents = FileManager.loadDocuments();
    private Document documentToUpdate;
    @FXML
    private TextField DocumentAuthorField;

    @FXML
    private TextField DocumentIDField;

    @FXML
    private TextField DocumentNameField;

    @FXML
    private TextField DocumentQuantityField;

    @FXML
    void returnUpdateDocumentScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("updateDocumentScene1.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void updateDocumentButton(ActionEvent event) throws IOException {
        if (!DocumentNameField.getText().isEmpty()) {
            documentToUpdate.setName(DocumentNameField.getText());
        }
        if (!DocumentAuthorField.getText().isEmpty()) {
            documentToUpdate.setAuthor(DocumentAuthorField.getText());
        }
        if (!DocumentQuantityField.getText().isEmpty()) {
            try {
                int DocumentQuantity = Integer.parseInt(DocumentQuantityField.getText());
                documentToUpdate.setQuantity(DocumentQuantity);
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Quantity");
                alert.setHeaderText(null);
                alert.setContentText("The Quantity must be a valid integer!");
                alert.showAndWait();
                return;
            }
        }
        for (int i = 0; i < Documents.size(); i++) {
            if (Documents.get(i).getId().equals(documentToUpdate.getId())) {
                Documents.set(i, documentToUpdate);
                break;
            }
        }
        FileManager.saveDocuments(Documents);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("The document has been successfully updated.");
        alert.setTitle("Document Updated");
        alert.setHeaderText(null);
        alert.setOnHidden(evt -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("updateDocumentScene1.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        alert.showAndWait();
    }

    public void setDocumentToUpdate(Document document) {
        this.documentToUpdate = document;
        setPrompText(document);
    }

    public void setPrompText(Document document) {
        DocumentIDField.setText(document.getId());
        DocumentIDField.setEditable(false);
        DocumentNameField.setPromptText(document.getName());
        DocumentAuthorField.setPromptText(document.getAuthor());
        DocumentQuantityField.setPromptText(Integer.toString(document.getQuantity()));
    }
}
