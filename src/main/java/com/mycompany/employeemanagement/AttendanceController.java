package com.mycompany.employeemanagement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;   //11 version 8.0 netbeans
import javafx.scene.Node;

public class AttendanceController {

    @FXML
    private TableView<EmployeeAttend> employeeTable;

    @FXML
    private TableColumn<EmployeeAttend, Integer> idColumn;

    @FXML
    private TableColumn<EmployeeAttend, String> firstNameColumn;

    @FXML
    private TableColumn<EmployeeAttend, String> lastNameColumn;

    @FXML
    private TableColumn<EmployeeAttend, String> dateColumn;

    @FXML
    private TableColumn<EmployeeAttend, String> startTimeColumn;

    @FXML
    private TableColumn<EmployeeAttend, String> endTimeColumn;

    @FXML
    private TableColumn<EmployeeAttend, String> statusColumn;

    @FXML
    private TableColumn<EmployeeAttend, String> totalHoursColumn;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private ComboBox<String> startTimePicker;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private ComboBox<String> endTimePicker;

    @FXML
    private Button close;

    private ObservableList<EmployeeAttend> employeeData = FXCollections.observableArrayList();

    @FXML
    public void close() {
        System.exit(0);
    }
    
    @FXML
    void backAction(ActionEvent event) throws IOException {
          Parent view3=FXMLLoader.load(getClass().getResource("dashboard.fxml"));
                Scene scene3=new Scene(view3);
                Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene3);
               window.show();

    }

    
  

    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        startTimeColumn.setCellValueFactory(cellData -> cellData.getValue().startTimeProperty());
        endTimeColumn.setCellValueFactory(cellData -> cellData.getValue().endTimeProperty());
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        totalHoursColumn.setCellValueFactory(cellData -> cellData.getValue().totalHoursProperty());

        employeeTable.setItems(employeeData);

        initializeTimePickers();
    }

    private void initializeTimePickers() {
        ObservableList<String> times = FXCollections.observableArrayList();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        for (int hour = 0; hour < 24; hour++) {
            for (int minute = 0; minute < 60; minute += 30) {
                times.add(LocalTime.of(hour, minute).format(formatter));
            }
        }

        startTimePicker.setItems(times);
        endTimePicker.setItems(times);
    }

    private void loadEmployeeData(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        employeeData.clear();
        try {
            Connection connection = database.connectDb();

            String query = "SELECT a.id, e.firstName, e.lastName, a.date, a.startTime, a.endTime, a.status, a.totalHours " +
                    "FROM attendance a JOIN employee e ON a.employee_id = e.employee_id " +
                    "WHERE a.date >= ? AND a.date <= ? AND a.startTime >= ? AND a.endTime <= ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDate(1, java.sql.Date.valueOf(startDate));
            statement.setDate(2, java.sql.Date.valueOf(endDate));
            statement.setTime(3, java.sql.Time.valueOf(startTime));
            statement.setTime(4, java.sql.Time.valueOf(endTime));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                employeeData.add(new EmployeeAttend(
                    resultSet.getInt("id"),
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getDate("date").toString(),
                    resultSet.getTime("startTime").toString(),
                    resultSet.getTime("endTime").toString(),
                    resultSet.getString("status"),
                    resultSet.getString("totalHours")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLoadAttendance() {
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();
        String startTimeString = startTimePicker.getValue();
        String endTimeString = endTimePicker.getValue();
        if (startDate != null && endDate != null && startTimeString != null && endTimeString != null) {
            LocalTime startTime = LocalTime.parse(startTimeString);
            LocalTime endTime = LocalTime.parse(endTimeString);
            loadEmployeeData(startDate, startTime, endDate, endTime);
        }
    }
    

  

    @FXML
    private void handlePresent() {
        EmployeeAttend selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
        if (selectedEmployee != null) {
            selectedEmployee.setStatus("Present");
            employeeTable.refresh();
        }
    }
    

    @FXML
    private void handleAbsent() {
        EmployeeAttend selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
        if (selectedEmployee != null) {
            selectedEmployee.setStatus("Absent");
            employeeTable.refresh();
        }
        
        
    }
}
