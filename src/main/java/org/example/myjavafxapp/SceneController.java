package org.example.myjavafxapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.control.Label;

import java.io.IOException;

public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToAddDocumentScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("addDocumentScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void returnCenterScene(ActionEvent event) throws  IOException {
        root = FXMLLoader.load(getClass().getResource("centerScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToRemoveDocumentScene(ActionEvent event) throws  IOException {
        root = FXMLLoader.load(getClass().getResource("removeDocumentScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToUpdateDocumentScene(ActionEvent event) throws  IOException {
        root = FXMLLoader.load(getClass().getResource("updateDocumentScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
