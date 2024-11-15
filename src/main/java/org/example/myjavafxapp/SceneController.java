package org.example.myjavafxapp;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Node;

import java.io.IOException;

public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void switchToAddDocumentScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("addDocumentScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToRemoveDocumentScene(ActionEvent event) throws  IOException {
        root = FXMLLoader.load(getClass().getResource("removeDocumentScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToUpdateDocumentScene(ActionEvent event) throws  IOException {
        root = FXMLLoader.load(getClass().getResource("updateDocumentScene1.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToFindDocumentScene(ActionEvent event) throws  IOException {
        root = FXMLLoader.load(getClass().getResource("findDocumentScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToDisplayDocumentScene(ActionEvent event) throws  IOException {
        root = FXMLLoader.load(getClass().getResource("displayDocumentScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void exit(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }
}
