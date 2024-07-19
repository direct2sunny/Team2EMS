package com.mycompany.employeemanagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class EventsUserController implements Initializable {

    @FXML
    private Button evClose;

    @FXML
    private TextField evSearch;

    @FXML
    private TableView<eventData> eventTableView;

    @FXML
    private TableColumn<String, eventData> tcDate;

    @FXML
    private TableColumn<String, eventData> tcDesc;

    @FXML
    private TableColumn<String, eventData> tcEndDate;

    @FXML
    private TableColumn<Integer, eventData> tcEventId;

    @FXML
    private TableColumn<String, eventData> tcLocation;

    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;

    @FXML
    void addEmployeeSearch(KeyEvent event) {

    }

    @FXML
    void eventSelect(MouseEvent event) {

    }

    // Method to add event data to the ObservableList
    public ObservableList<eventData> addEventListData() {
        ObservableList<eventData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Event";

        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            eventData eventD;

            while (result.next()) {
                eventD = new eventData(
                        result.getInt("id"),
                        result.getString("description"),
                        result.getString("date"),
                        result.getString("end_date"),
                        result.getString("location")
                );
                listData.add(eventD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) result.close();
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listData;
    }

    private ObservableList<eventData> addEventList;

    private void addEventShowListData() {
        addEventList = addEventListData();

        tcEventId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        tcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tcEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        tcLocation.setCellValueFactory(new PropertyValueFactory<>("location"));


        eventTableView.setItems(addEventList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        addEventShowListData();

        evClose.setOnAction(e ->{
            Stage stage = (Stage)  evClose.getScene().getWindow();
            stage.close();
        });

    }

    @FXML
    public void addEmployeeSearch() {

        FilteredList<eventData> filter = new FilteredList<>(addEventList, e -> true);

        evSearch.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(predicateEventeData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (String.valueOf(predicateEventeData.getId()).contains(searchKey)) {
                    return true;
                } else if (predicateEventeData.getDate().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateEventeData.getLocation().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateEventeData.getDescription().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateEventeData.getEndDate().toLowerCase().contains(searchKey)) {
                    return true;

                } else {
                    return false;
                }
            });
        });

        SortedList<eventData> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(eventTableView.comparatorProperty());
        eventTableView.setItems(sortList);
    }
}
