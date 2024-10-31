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

public class AddDocumentController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField DocumentAuthorField;

    @FXML
    private TextField DocumentIDField;

    @FXML
    private TextField DocumentNameField;

    @FXML
    private TextField DocumentQuantityField;

    List<Document> Documents = FileManager.loadDocuments();

    @FXML
    public void addDocumentButton(ActionEvent event) throws IOException {
        if (DocumentIDField.getText().isEmpty() ||
                DocumentNameField.getText().isEmpty() ||
                DocumentAuthorField.getText().isEmpty() ||
                DocumentQuantityField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Missing Information");
            alert.setContentText("Please enter all required information in each field!");
            alert.showAndWait();
            return;
        }
        
        String DocumentName = DocumentNameField.getText();
        String DocumentID = DocumentIDField.getText();
        String DocumentAuthor = DocumentAuthorField.getText();
        int DocumentQuantity;

        for (Document doc : Documents) {
            if (DocumentID.equals(doc.getId())) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Duplicate ID");
                alert.setHeaderText(null);
                alert.setContentText("This ID already exists. Please enter a different ID.");
                alert.showAndWait();
                return;
            }
        }

        try {
            DocumentQuantity = Integer.parseInt(DocumentQuantityField.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Quantity");
            alert.setHeaderText(null);
            alert.setContentText("The Quantity must be a valid integer!");
            alert.showAndWait();
            return;
        }

        Documents.add(new Document(DocumentID, DocumentName, DocumentAuthor, DocumentQuantity));
        FileManager.saveDocuments(Documents);
        DocumentNameField.clear();
        DocumentIDField.clear();
        DocumentAuthorField.clear();
        DocumentQuantityField.clear();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("The document has been successfully removed.");
        alert.setTitle("Document Added");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    @FXML
    public void returnCenterScene(ActionEvent event) throws  IOException {
        root = FXMLLoader.load(getClass().getResource("centerScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
