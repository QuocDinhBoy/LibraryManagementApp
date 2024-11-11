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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.List;

public class FindDocumentController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private ObservableList<Document> documentList;

    @FXML
    private TableView <Document> table;
    @FXML
    private TableView <Document> table1;
    @FXML
    private TableColumn<Document, String> id_column;
    @FXML
    private TableColumn<Document, String> name_column;
    @FXML
    private TableColumn<Document, String> author_column;
    @FXML
    private TableColumn<Document, Integer> quantity_column;
    @FXML
    private TableColumn<Document, String> id_column1;
    @FXML
    private TableColumn<Document, String> name_column1;
    @FXML
    private TableColumn<Document, String> author_column1;
    @FXML
    private TableColumn<Document, Integer> quantity_column1;
    @FXML
    private TextField searchField;
    @FXML
    private TextField searchField1;
    @FXML
    private AnchorPane byNameAnchorPane;
    @FXML
    private AnchorPane byIdAnchorPane;
    @FXML
    private Button findbyid;
    @FXML
    private Button findbyname;

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

        id_column1.setCellValueFactory(new PropertyValueFactory<>("id"));
        name_column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        author_column1.setCellValueFactory(new PropertyValueFactory<>("author"));
        quantity_column1.setCellValueFactory(new PropertyValueFactory<>("quantity"));


        table.setItems(documentList);
        table1.setItems(documentList);
    }

    public void filterDocumentListByName(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            table.setItems(documentList);
        } else {
            ObservableList<Document> filteredList = FXCollections.observableArrayList();

            for(Document document : documentList) {
                if (document.getName().toLowerCase().contains(keyword.toLowerCase())) {
                    filteredList.add(document);
                }
            }
            table.setItems(filteredList);
        }
    }

    public void filterDocumentListById(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            table1.setItems(documentList);
        } else {
            ObservableList<Document> filteredList = FXCollections.observableArrayList();

            for(Document document : documentList) {
                if (document.getId().toLowerCase().contains(keyword.toLowerCase())) {
                    filteredList.add(document);
                }
            }
            table1.setItems(filteredList);
        }
    }

    @FXML
    public void initialize() {
        setTableView();
        searchField1.textProperty().addListener((observable, oldValue, newValue) -> {
            filterDocumentListById(newValue);});
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filterDocumentListByName(newValue);});
    }

    public void switchForm(ActionEvent event) {
        if(event.getSource() == findbyname) {
            byNameAnchorPane.setVisible(true);
            byIdAnchorPane.setVisible(false);
            findbyname.setStyle("-fx-background-color:linear-gradient(to bottom right, #52ae8b, #9a2d3d)");
            findbyid.setStyle("-fx-background-color:transparent");
        } else if(event.getSource() == findbyid) {
            byIdAnchorPane.setVisible(true);
            byNameAnchorPane.setVisible(false);
            findbyid.setStyle("-fx-background-color:linear-gradient(to bottom right, #52ae8b, #9a2d3d)");
            findbyname.setStyle("-fx-background-color:transparent");
        }
    }
}
