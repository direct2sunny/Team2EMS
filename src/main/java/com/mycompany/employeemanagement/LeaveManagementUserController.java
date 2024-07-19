package com.mycompany.employeemanagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class LeaveManagementUserController implements Initializable {

    // FXML annotations to bind the UI components
    @FXML
    private Button lAdd;

    @FXML
    private Button lClear;

    @FXML
    private Button lDel;

    @FXML
    private Button lUpdate;

    @FXML
    private TableView<LeaveRequest> leaveTableView;

    @FXML
    private TableColumn<LeaveRequest, String> tcDescription;

    @FXML
    private TableColumn<LeaveRequest, Date> tcEndDate;

    @FXML
    private TableColumn<LeaveRequest, Integer> tcLeaveID;

    @FXML
    private TableColumn<LeaveRequest, Integer> tcNumDays;

    @FXML
    private TableColumn<LeaveRequest, Date> tcStartDate;

    @FXML
    private TableColumn<LeaveRequest, String> tcStatus;

    @FXML
    private TextField tfDescription;

    @FXML
    private TextField tfEndDate;

    @FXML
    private TextField tfLeaveId;

    @FXML
    private TextField tfStartDate;

    // Class variables for database connection and queries
    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;

    // Method to handle the selection of a leave request from the TableView
    @FXML
    void leaveSelect(MouseEvent event) {
        // Code for handling the selection
        LeaveRequest eData = leaveTableView.getSelectionModel().getSelectedItem();
        int num = leaveTableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        tfStartDate.setText(convertDateToString(String.valueOf(eData.getStartDate())));
        tfEndDate.setText(convertDateToString(String.valueOf(eData.getEndDate())));
        tfLeaveId.setText(eData.getId()+"");
        tfDescription.setText(eData.getDescription());
    }

    // Method to add a leave request to the database
    private void addLeave() {
        // Retrieve data from text fields
        int leaveId = Integer.parseInt(tfLeaveId.getText());
        String desc = tfDescription.getText();
        String startDate = tfStartDate.getText();
        String endDate = tfEndDate.getText();

        // SQL query for inserting a new leave request
        String sql = "INSERT INTO leave_request(id, employee_id, start_date, end_date, description) VALUES (?, ?, ?, ?, ?)";

        // Connect to the database
        connect = database.connectDb();

        try {
            Alert alert;
            // Check if any of the text fields are empty
            if (tfLeaveId.getText().isEmpty()
                    || tfDescription.getText().isEmpty()
                    || tfEndDate.getText().isEmpty()
                    || tfStartDate.getText().isEmpty()) {
                // Show error alert if any field is empty
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                // Check if the leave ID already exists
                String check = "SELECT id FROM leave_request WHERE id = " + leaveId;

                statement = connect.createStatement();
                result = statement.executeQuery(check);

                if (result.next()) {
                    // Show error alert if leave ID already exists
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Leave ID: " + leaveId + " already exists!");
                    alert.showAndWait();
                } else {
                    // Convert string dates to java.sql.Date
                    Date sDt = convertStringToSqlDate(startDate);
                    Date eDt = convertStringToSqlDate(endDate);

                    // Prepare the SQL statement
                    prepare = connect.prepareStatement(sql);
                    prepare.setInt(1, leaveId);
                    prepare.setInt(2, EmployeeIdSingleton.getInstance().getEmployeeId());
                    prepare.setDate(3, sDt);
                    prepare.setDate(4, eDt);
                    prepare.setString(5, desc);

                    // Execute the SQL statement
                    prepare.executeUpdate();

                    // Show success alert
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();

                    // Update the leave list with the new leave request
                    leaveList.add(getLeaveRequestById(leaveId));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve leave requests from the database
    public ObservableList<LeaveRequest> leaveListData() {
        ObservableList<LeaveRequest> listData = FXCollections.observableArrayList();

        // SQL query to select all leave requests
        String sql = "SELECT * FROM leave_request";

        // Connect to the database
        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            LeaveRequest leaveRequest;

            while (result.next()) {
                // Create a LeaveRequest object for each row in the result set
                leaveRequest = new LeaveRequest(
                        result.getInt("id"),
                        result.getDate("start_date"),
                        result.getDate("end_date"),
                        result.getString("description"),
                        result.getInt("numDays"),
                        result.getShort("status")
                );

                // Set the status string based on the status value
                if (leaveRequest.getStatus() == 0) {
                    leaveRequest.setSt("Rejected");
                } else if (leaveRequest.getStatus() == 1) {
                    leaveRequest.setSt("Pending");
                } else {
                    leaveRequest.setSt("Approved");
                }

                // Add the LeaveRequest object to the list
                listData.add(leaveRequest);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    // Method to retrieve a leave request by ID
    public LeaveRequest getLeaveRequestById(int id) {
        LeaveRequest leaveRequest = null;

        // SQL query to select a leave request by ID
        String sql = "SELECT * FROM leave_request WHERE id =" + id;
        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                // Create a LeaveRequest object for the selected row
                leaveRequest = new LeaveRequest(
                        result.getInt("id"),
                        result.getDate("start_date"),
                        result.getDate("end_date"),
                        result.getString("description"),
                        result.getInt("numDays"),
                        result.getShort("status")

                );

                // Set the status string based on the status value
                if (leaveRequest.getStatus() == 0) {
                    leaveRequest.setSt("Rejected");
                } else if (leaveRequest.getStatus() == 1) {
                    leaveRequest.setSt("Pending");
                } else {
                    leaveRequest.setSt("Approved");
                }
            }



        } catch (Exception e) {
            e.printStackTrace();
        }
        return leaveRequest;
    }

    // ObservableList to hold the leave requests
    private ObservableList<LeaveRequest> leaveList;

    // Method to display leave requests in the TableView
    public void leaveShowListData() {
        leaveList = leaveListData();

        // Set cell value factories for the TableView columns
        tcLeaveID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        tcEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        tcDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tcNumDays.setCellValueFactory(new PropertyValueFactory<>("numDays"));
        tcStatus.setCellValueFactory(new PropertyValueFactory<>("st"));

        // Set the items for the TableView
        leaveTableView.setItems(leaveList);
    }


    private void updateLeave(){

        LeaveRequest eData = leaveTableView.getSelectionModel().getSelectedItem();
        int oldId = eData.getId();
        int leaveId = Integer.parseInt(tfLeaveId.getText());
        String desc = tfDescription.getText();
        String startDate = tfStartDate.getText();
        String endDate = tfEndDate.getText();



        String sql = "UPDATE leave_request SET id = ?, description=?, start_date=?, end_date=?, description=? WHERE id="+oldId;

        connect = database.connectDb();
        try {
            Alert alert;
            if (tfLeaveId.getText().isEmpty()
                    || tfDescription.getText().isEmpty()
                    || tfEndDate.getText().isEmpty()
                    || tfStartDate.getText().isEmpty()
            ) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                if(oldId == leaveId){

                    String check = "SELECT id FROM leave_request WHERE id = "+oldId;

                    statement = connect.createStatement();
                    result = statement.executeQuery(check);

                    if (result.next()) {

                        // Convert string dates to java.sql.Date
                        Date sDt = convertStringToSqlDate(startDate);
                        Date eDt = convertStringToSqlDate(endDate);

                        // Prepare the SQL statement
                        prepare = connect.prepareStatement(sql);
                        prepare.setInt(1, oldId);
                        prepare.setInt(2, EmployeeIdSingleton.getInstance().getEmployeeId());
                        prepare.setDate(3, sDt);
                        prepare.setDate(4, eDt);
                        prepare.setString(5, desc);

                        int row = prepare.executeUpdate();

                        if (row > 0){
                            alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Information Message");
                            alert.setHeaderText(null);
                            alert.setContentText("Successfully Updated!");
                            alert.showAndWait();

                            // Update the eventData object in the ObservableList
                            eData.setDescription(desc);
                            eData.setStartDate(sDt);
                            eData.setEndDate(eDt);

                            eData.setNumDays(getLeaveRequestById(oldId).getNumDays());

                            // Refresh the TableView
                            leaveTableView.refresh();
                        }



                    } else {

                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Leave ID: " + oldId + " not found to update in database");
                        alert.showAndWait();
                    }
                }else{

                    String check = "SELECT id FROM leave_request WHERE id = "+leaveId;

                    statement = connect.createStatement();
                    result = statement.executeQuery(check);

                    if (result.next()) {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Leave ID: " + leaveId + " was already exist!");
                        alert.showAndWait();
                    }else{


                        Date sDt = convertStringToSqlDate(startDate);
                        Date eDt = convertStringToSqlDate(endDate);

                        // Prepare the SQL statement
                        prepare = connect.prepareStatement(sql);
                        prepare.setInt(1, leaveId);
                        prepare.setInt(2, EmployeeIdSingleton.getInstance().getEmployeeId());
                        prepare.setDate(3, sDt);
                        prepare.setDate(4, eDt);
                        prepare.setString(5, desc);

                        int row = prepare.executeUpdate();

                        if (row > 0){
                            alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Information Message");
                            alert.setHeaderText(null);
                            alert.setContentText("Successfully Updated!");
                            alert.showAndWait();

                            // Update the eventData object in the ObservableList
                            eData.setId(leaveId);
                            eData.setDescription(desc);
                            eData.setStartDate(sDt);
                            eData.setEndDate(eDt);

                            eData.setNumDays(getLeaveRequestById(leaveId).getNumDays());

                            // Refresh the TableView
                            leaveTableView.refresh();
                        }

                    }


                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    private void deleteLeave() throws SQLException {
        Alert alert;
        connect = database.connectDb();

        if (tfLeaveId.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Leave Id must not be empty");
            alert.showAndWait();
        }else{
            int id = Integer.parseInt(tfLeaveId.getText());
            String sql = "DELETE FROM leave_request WHERE id="+id;
            prepare = connect.prepareStatement(sql);
            int row = prepare.executeUpdate();

            if(row > 0){
                LeaveRequest eData = leaveTableView.getSelectionModel().getSelectedItem();
                leaveList.remove(eData);
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Removed!");
                alert.showAndWait();
                clearFields();
            }

        }


    }

    private void clearFields(){
        tfLeaveId.clear();
        tfStartDate.clear();
        tfEndDate.clear();
        tfDescription.clear();

    }

    // Method to initialize the controller
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Show leave requests data in the TableView
        leaveShowListData();

        // Set action for the add button
        lAdd.setOnAction(e -> {
            addLeave();
        });


        lUpdate.setOnAction(e ->{
            updateLeave();
        });


        lDel.setOnAction(e ->{
            try {
                deleteLeave();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        lClear.setOnAction(e ->{
            clearFields();
        });
    }

    // Method to convert a string to java.sql.Date
    public static java.sql.Date convertStringToSqlDate(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            // Parse the date string to a java.util.Date object
            java.util.Date utilDate = sdf.parse(dateString);
            // Convert java.util.Date to java.sql.Date
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            return sqlDate;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Converts a date string from "yyyy-MM-dd" format to "dd/MM/yyyy" format.
     *
     * @param dateString The date string in "yyyy-MM-dd" format.
     * @return The date string in "dd/MM/yyyy" format.
     */
    public static String convertDateToString(String dateString) {
        // Define the input and output date formats
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            // Parse the input date string to a Date object
            java.util.Date date = inputFormat.parse(dateString);
            // Format the Date object to the desired output format
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            // Return null or handle the exception as needed
            return null;
        }
    }

}
