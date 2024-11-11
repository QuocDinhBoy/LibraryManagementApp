package org.example.myjavafxapp;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

public class DisplayDocumentController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private ObservableList<Document> documentList;

    @FXML
    private TableView <Document> table;
    @FXML
    private TableColumn<Document, String> id_column;
    @FXML
    private TableColumn<Document, String> name_column;
    @FXML
    private TableColumn<Document, String> author_column;
    @FXML
    private TableColumn<Document, Integer> quantity_column;

    @FXML
    public void returnCenterScene(ActionEvent event) throws  IOException {
        root = FXMLLoader.load(getClass().getResource("centerScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void exit(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    public void setTableView() {
        documentList = FXCollections.observableArrayList(FileManager.loadDocuments());

        id_column.setCellValueFactory(new PropertyValueFactory<>("id"));
        name_column.setCellValueFactory(new PropertyValueFactory<>("name"));
        author_column.setCellValueFactory(new PropertyValueFactory<>("author"));
        quantity_column.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        table.setItems(documentList);
    }
    public void initialize() {
        setTableView();
    }
}
