package org.example.myjavafxapp;

import javafx.application.Platform;
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

public class UpdateDocumentController1 {

    private Stage stage;
    private Scene scene;
    private Parent root;
    List<Document> Documents = FileManager.loadDocuments();
    Document documentToUpdate = null;

    @FXML
    private TextField DocumentIDField;

    @FXML
    public void returnCenterScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("centerScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void nextButton(ActionEvent event) throws IOException {
        if (DocumentIDField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Missing ID");
            alert.setHeaderText(null);
            alert.setContentText("Please enter an ID to remove");
            alert.showAndWait();
            return;
        }

        String documentID = DocumentIDField.getText();
        for (Document doc : Documents) {
            if (documentID.equals(doc.getId())) {
                documentToUpdate = doc;
                break;
            }
        }
        if (documentToUpdate == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Document Not Found");
            alert.setHeaderText(null);
            alert.setContentText("No document found with the specified ID.");
            alert.showAndWait();
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("updateDocumentScene2.fxml"));
            root = loader.load();
            UpdateDocumentController2 updateController = loader.getController();
            updateController.setDocumentToUpdate(documentToUpdate);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void exit(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }
}
