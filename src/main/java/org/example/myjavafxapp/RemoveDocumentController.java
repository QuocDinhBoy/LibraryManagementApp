package org.example.myjavafxapp;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class RemoveDocumentController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    List<Document> Documents = FileManager.loadDocuments();

    @FXML
    private TextField DocumentIDField;

    @FXML
    void removeDocumentButton(ActionEvent event) throws IOException {
        if (DocumentIDField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Missing ID");
            alert.setHeaderText(null);
            alert.setContentText("Please enter an ID to remove");
            alert.showAndWait();
            return;
        }
        String documentID = DocumentIDField.getText();
        Document documentToRemove = null;
        for (Document doc : Documents) {
            if (documentID.equals(doc.getId())) {
                documentToRemove = doc;
                break;
            }
        }
        if (documentToRemove == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Document Not Found");
            alert.setHeaderText(null);
            alert.setContentText("No document found with the specified ID.");
            alert.showAndWait();
        } else {
            DocumentIDField.clear();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Document Removed");
            alert.setHeaderText("Are you sure want to remove this document?");
            alert.setContentText(
                    "Document ID: " + documentToRemove.getId() + "\n" +
                            "Name: " + documentToRemove.getName() + "\n" +
                            "Author: " + documentToRemove.getAuthor() + "\n" +
                            "Quantity: " + documentToRemove.getQuantity() + "\n"
            );
            ButtonType yesButton = new ButtonType("Yes");
            ButtonType noButton = new ButtonType("No");
            alert.getButtonTypes().setAll(yesButton,noButton);
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == yesButton) {
                Documents.remove(documentToRemove);
                FileManager.saveDocuments(Documents);
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Success");
                successAlert.setHeaderText(null);
                successAlert.setContentText("The document has been successfully removed.");
                successAlert.showAndWait();
            } else {
                return;
            }
        }
    }

    @FXML
    public void returnCenterScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("centerScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void exit(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }
}
