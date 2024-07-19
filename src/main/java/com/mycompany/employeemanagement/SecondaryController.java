package com.mycompany.employeemanagement;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;   //11 version 8.0 netbeans
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author GagandeepSingh
 * 
 * Thanks for the support guys! <3
 */
public class SecondaryController implements Initializable {

    @FXML
    private AnchorPane main_form;
    
    @FXML
    private ComboBox<?> shiftType;

    @FXML
    private DatePicker dob;

    @FXML
    private Button close;

    @FXML
    private Button minimize;

    @FXML
    private Label username;

    @FXML
    private Button home_btn;

    @FXML
    private Button addEmployee_btn;

    @FXML
    private Button salary_btn;

    @FXML
    private Button logout;

    @FXML
    private AnchorPane home_form;

    @FXML
    private Label home_totalEmployees;

    @FXML
    private Label home_totalPresents;

    @FXML
    private Label home_totalInactiveEm;

    @FXML
    private BarChart<?, ?> home_chart;

    @FXML
    private AnchorPane addEmployee_form;
    
    @FXML
    private AnchorPane event_form;

    @FXML
    private TableView<employeeData> addEmployee_tableView;

    @FXML
    private TableColumn<employeeData, String> addEmployee_col_employeeID;

    @FXML
    private TableColumn<employeeData, String> addEmployee_col_firstName;

    @FXML
    private TableColumn<employeeData, String> addEmployee_col_lastName;

    @FXML
    private TableColumn<employeeData, String> addEmployee_col_gender;

    @FXML
    private TableColumn<employeeData, String> addEmployee_col_phoneNum;
    @FXML
    private TableColumn<employeeData, String> addEmployee_col_DOB;

    @FXML
    private TableColumn<employeeData, String> addEmployee_col_type;
    @FXML
    private Button back;
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
    
     @FXML
    private TextField tfDate;

    @FXML
    private TextField tfDesc;

    @FXML
    private TextField tfEndDate;

    @FXML
    private TextField tfEventId;

    @FXML
    private TextField tfLocation;
    
    
     @FXML
    private Button evAdd;

    @FXML
    private Button evClear;

    @FXML
    private Button evDel;

    @FXML
    private TextField evSearch;

    @FXML
    private Button evUpdate;

    @FXML
    private TableColumn<employeeData, String> addEmployee_col_position;

    @FXML
    private TableColumn<employeeData, String> addEmployee_col_date;

    @FXML
    private TextField addEmployee_search;

    @FXML
    private TextField addEmployee_employeeID;

    @FXML
    private TextField addEmployee_firstName;

    @FXML
    private TextField addEmployee_lastName;

    @FXML
    private ComboBox<?> addEmployee_gender;

    @FXML
    private TextField addEmployee_phoneNum;

    @FXML
    private ComboBox<?> addEmployee_position;

    @FXML
    private ImageView addEmployee_image;
    @FXML
    private Button attend;
    @FXML
    private Button addEmployee_importBtn;

    @FXML
    private Button addEmployee_addBtn;

    @FXML
    private Button addEmployee_updateBtn;

    @FXML
    private Button addEmployee_deleteBtn;

    @FXML
    private Button addEmployee_clearBtn;

    @FXML
    private AnchorPane salary_form;

    @FXML
    private TextField salary_employeeID;

    @FXML
    private Label salary_firstName;

    @FXML
    private Label salary_lastName;

    @FXML
    private Label salary_position;

    @FXML
    private TextField salary_salary;

    @FXML
    private Button salary_updateBtn;

    @FXML
    private Button salary_clearBtn;
    
    @FXML
    private Button event_btn;

    @FXML
    private TableView<employeeData> salary_tableView;

    @FXML
    private TableColumn<employeeData, String> salary_col_employeeID;

    @FXML
    private TableColumn<employeeData, String> salary_col_firstName;

    @FXML
    private TableColumn<employeeData, String> salary_col_lastName;

    @FXML
    private TableColumn<employeeData, String> salary_col_position;

    @FXML
    private TableColumn<employeeData, String> salary_col_salary;

    @FXML
    private TableView<LeaveRequest> leaveTableView;
    @FXML
    private TableColumn<LeaveRequest, Integer> tcNumDays;

    @FXML
    private TableColumn<LeaveRequest, java.sql.Date> tcStartDate;

    @FXML
    private TableColumn<LeaveRequest, String> tcStatus;

    @FXML
    private TableColumn<LeaveRequest, String> tcUsername;

    @FXML
    private TableColumn<LeaveRequest, java.sql.Date> tcEndDt;

    @FXML
    private TableColumn<LeaveRequest, String> tcDescription;

    @FXML
    private AnchorPane leave_form;

    @FXML
    private Button leave_btn;


    @FXML
    private Button lApprove;

    @FXML
    private Button lReject;


    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;

    private Image image;
    
    //private 

    public void homeTotalEmployees() {

        String sql = "SELECT COUNT(id) FROM employee";

        connect = database.connectDb();
        int countData = 0;
        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                countData = result.getInt("COUNT(id)");
            }

            home_totalEmployees.setText(String.valueOf(countData));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void homeEmployeeTotalPresent() {

        String sql = "SELECT COUNT(id) FROM employee_info";

        connect = database.connectDb();
        int countData = 0;
        try {
            statement = connect.createStatement();
            result = statement.executeQuery(sql);

            while (result.next()) {
                countData = result.getInt("COUNT(id)");
            }
            home_totalPresents.setText(String.valueOf(countData));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void homeTotalInactive() {

        String sql = "SELECT COUNT(id) FROM employee_info WHERE salary = '0.0'";

        connect = database.connectDb();
        int countData = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                countData = result.getInt("COUNT(id)");
            }
            home_totalInactiveEm.setText(String.valueOf(countData));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void homeChart() {

        home_chart.getData().clear();

        String sql = "SELECT date, COUNT(id) FROM employee GROUP BY date ORDER BY TIMESTAMP(date) ASC LIMIT 7";

        connect = database.connectDb();

        try {
            XYChart.Series chart = new XYChart.Series();

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
            }

            home_chart.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @FXML
    void attendance(ActionEvent event) throws IOException
    {
        Parent view3=FXMLLoader.load(getClass().getResource("attendance.fxml"));
                Scene scene3=new Scene(view3);
                Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene3);
               window.show();
    }
    
    
     @FXML
    void events(ActionEvent event) throws IOException {
        Parent view3=FXMLLoader.load(getClass().getResource("events.fxml"));
                Scene scene3=new Scene(view3);
                Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene3);
               window.show();

    }

    @FXML
    public void addEmployeeAdd() {

        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        
        // Create a LocalDate object
        LocalDate datedob = dob.getValue();

        // Create a DateTimeFormatter with the desired date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // Convert the LocalDate to String
        
        String dateString = datedob.format(formatter);
        String sql = "INSERT INTO employee "
                + "(employee_id,firstName,lastName,gender,phoneNum,position,image,date,type,DOB) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?)";

        connect = database.connectDb();

        try {
            Alert alert;
            if (addEmployee_employeeID.getText().isEmpty()
                    || addEmployee_firstName.getText().isEmpty()
                    || addEmployee_lastName.getText().isEmpty()
                    || addEmployee_gender.getSelectionModel().getSelectedItem() == null
                    || addEmployee_phoneNum.getText().isEmpty()
                    || shiftType.getSelectionModel().getSelectedItem() == null
                    || addEmployee_position.getSelectionModel().getSelectedItem() == null
                    || getData.path == null || getData.path == ""
                    ||dob.getValue()==null) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {

                String check = "SELECT employee_id FROM employee WHERE employee_id = '"
                        + addEmployee_employeeID.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(check);

                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Employee ID: " + addEmployee_employeeID.getText() + " was already exist!");
                    alert.showAndWait();
                } else {

                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, addEmployee_employeeID.getText());
                    prepare.setString(2, addEmployee_firstName.getText());
                    prepare.setString(3, addEmployee_lastName.getText());
                    prepare.setString(4, (String) addEmployee_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(5, addEmployee_phoneNum.getText());
                    prepare.setString(6, (String) addEmployee_position.getSelectionModel().getSelectedItem());

                    String uri = getData.path;
                    uri = uri.replace("\\", "\\\\");

                    prepare.setString(7, uri);
                    prepare.setString(8, String.valueOf(sqlDate));
                    prepare.setString(9, (String) shiftType.getSelectionModel().getSelectedItem());
                    prepare.setString(10, dateString);
                    prepare.executeUpdate();

                    String insertInfo = "INSERT INTO employee_info "
                            + "(employee_id,firstName,lastName,position,salary,date) "
                            + "VALUES(?,?,?,?,?,?)";

                    prepare = connect.prepareStatement(insertInfo);
                    prepare.setString(1, addEmployee_employeeID.getText());
                    prepare.setString(2, addEmployee_firstName.getText());
                    prepare.setString(3, addEmployee_lastName.getText());
                    prepare.setString(4, (String) addEmployee_position.getSelectionModel().getSelectedItem());
                    prepare.setString(5, "0.0");
                    prepare.setString(6, String.valueOf(sqlDate));
                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();

                    addEmployeeShowListData();
                    addEmployeeReset();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void addEmployeeUpdate() {

        String uri = getData.path;
        uri = uri.replace("\\", "\\\\");

        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        String sql = "UPDATE employee SET firstName = '"
                + addEmployee_firstName.getText() + "', lastName = '"
                + addEmployee_lastName.getText() + "', gender = '"
                + addEmployee_gender.getSelectionModel().getSelectedItem() + "', phoneNum = '"
                + addEmployee_phoneNum.getText() + "', position = '"
                + addEmployee_position.getSelectionModel().getSelectedItem() + "', image = '"
                + uri + "', date = '" + sqlDate + "' WHERE employee_id ='"
                + addEmployee_employeeID.getText() + "'";

        connect = database.connectDb();

        try {
            Alert alert;
            if (addEmployee_employeeID.getText().isEmpty()
                    || addEmployee_firstName.getText().isEmpty()
                    || addEmployee_lastName.getText().isEmpty()
                    || addEmployee_gender.getSelectionModel().getSelectedItem() == null
                    || addEmployee_phoneNum.getText().isEmpty()
                    || addEmployee_position.getSelectionModel().getSelectedItem() == null
                    || getData.path == null || getData.path == "") {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Cofirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Employee ID: " + addEmployee_employeeID.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    double salary = 0;

                    String checkData = "SELECT * FROM employee_info WHERE employee_id = '"
                            + addEmployee_employeeID.getText() + "'";

                    prepare = connect.prepareStatement(checkData);
                    result = prepare.executeQuery();

                    while (result.next()) {
                        salary = result.getDouble("salary");
                    }

                    String updateInfo = "UPDATE employee_info SET firstName = '"
                            + addEmployee_firstName.getText() + "', lastName = '"
                            + addEmployee_lastName.getText() + "', position = '"
                            + addEmployee_position.getSelectionModel().getSelectedItem()
                            + "' WHERE employee_id = '"
                            + addEmployee_employeeID.getText() + "'";

                    prepare = connect.prepareStatement(updateInfo);
                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    addEmployeeShowListData();
                    addEmployeeReset();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void addEmployeeDelete() {

        String sql = "DELETE FROM employee WHERE employee_id = '"
                + addEmployee_employeeID.getText() + "'";

        connect = database.connectDb();

        try {

            Alert alert;
            if (addEmployee_employeeID.getText().isEmpty()
                    || addEmployee_firstName.getText().isEmpty()
                    || addEmployee_lastName.getText().isEmpty()
                    || addEmployee_gender.getSelectionModel().getSelectedItem() == null
                    || addEmployee_phoneNum.getText().isEmpty()
                    || addEmployee_position.getSelectionModel().getSelectedItem() == null
                    || getData.path == null || getData.path == "") {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Cofirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Employee ID: " + addEmployee_employeeID.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    String deleteInfo = "DELETE FROM employee_info WHERE employee_id = '"
                            + addEmployee_employeeID.getText() + "'";

                    prepare = connect.prepareStatement(deleteInfo);
                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();

                    addEmployeeShowListData();
                    addEmployeeReset();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void addEmployeeReset() {
        addEmployee_employeeID.setText("");
        addEmployee_firstName.setText("");
        addEmployee_lastName.setText("");
        addEmployee_gender.getSelectionModel().clearSelection();
        addEmployee_position.getSelectionModel().clearSelection();
        addEmployee_phoneNum.setText("");
        addEmployee_image.setImage(null);
        getData.path = "";
    }

    @FXML
    public void addEmployeeInsertImage() {

        FileChooser open = new FileChooser();
        File file = open.showOpenDialog(main_form.getScene().getWindow());

        if (file != null) {
            getData.path = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 101, 127, false, true);
            addEmployee_image.setImage(image);
        }
    }

    private String[] positionList = {"Marketer Coordinator", "Web Developer (Back End)", "Web Developer (Front End)", "App Developer"};

    @FXML
    public void addEmployeePositionList() {
        List<String> listP = new ArrayList<>();

        for (String data : positionList) {
            listP.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listP);
        addEmployee_position.setItems(listData);
    }
    
    private String[] positiontype = {"Part Time", "Full Time"};

    @FXML
    public void employeeType() {
        List<String> listT= new ArrayList<>();

        for (String data : positiontype) {
            listT.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listT);
        shiftType.setItems(listData);
    }

    private String[] listGender = {"Male", "Female", "Others"};

    @FXML
    public void addEmployeeGendernList() {
        List<String> listG = new ArrayList<>();

        for (String data : listGender) {
            listG.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listG);
        addEmployee_gender.setItems(listData);
    }

    @FXML
    public void addEmployeeSearch() {

        FilteredList<employeeData> filter = new FilteredList<>(addEmployeeList, e -> true);

        addEmployee_search.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(predicateEmployeeData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateEmployeeData.getEmployeeId().toString().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getFirstName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getLastName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getGender().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getPhoneNum().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getPosition().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getDate().toString().contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<employeeData> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(addEmployee_tableView.comparatorProperty());
        addEmployee_tableView.setItems(sortList);
    }

    @FXML
    public void addEventSearch() {


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

    public ObservableList<employeeData> addEmployeeListData() {

        ObservableList<employeeData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM employee";

        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            employeeData employeeD;

            while (result.next()) {
                employeeD = new employeeData(result.getInt("employee_id"),
                        result.getString("firstName"),
                        result.getString("lastName"),
                        result.getString("gender"),
                        result.getString("phoneNum"),
                        result.getString("position"),
                        result.getString("image"),
                        result.getDate("date"),
                        result.getString("type"),
              result.getDate("DOB"));
                listData.add(employeeD);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }
    
    
    
    private ObservableList<employeeData> addEmployeeList;

    public void addEmployeeShowListData() {
        addEmployeeList = addEmployeeListData();

        addEmployee_col_employeeID.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        addEmployee_col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        addEmployee_col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        addEmployee_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        addEmployee_col_phoneNum.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));
        addEmployee_col_position.setCellValueFactory(new PropertyValueFactory<>("position"));
        addEmployee_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        addEmployee_col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        addEmployee_col_DOB.setCellValueFactory(new PropertyValueFactory<>("DOB"));            


        addEmployee_tableView.setItems(addEmployeeList);

    }

    @FXML
    public void addEmployeeSelect() {
        employeeData employeeD = addEmployee_tableView.getSelectionModel().getSelectedItem();
        int num = addEmployee_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        addEmployee_employeeID.setText(String.valueOf(employeeD.getEmployeeId()));
        addEmployee_firstName.setText(employeeD.getFirstName());
        addEmployee_lastName.setText(employeeD.getLastName());
        addEmployee_phoneNum.setText(employeeD.getPhoneNum());

        getData.path = employeeD.getImage();

        String uri = "file:" + employeeD.getImage();

        image = new Image(uri, 101, 127, false, true);
        addEmployee_image.setImage(image);
    }


    @FXML
    public void eventSelect() {
        eventData eData = eventTableView.getSelectionModel().getSelectedItem();
        int num = eventTableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        tfDate.setText(eData.getDate());
        tfDesc.setText(eData.getDescription());
        tfEventId.setText(eData.getId()+"");
        tfLocation.setText(eData.getLocation());
        tfEndDate.setText(eData.getEndDate());

    }


    @FXML
    public void salaryUpdate() {

        String sql = "UPDATE employee_info SET salary = '" + salary_salary.getText()
                + "' WHERE employee_id = '" + salary_employeeID.getText() + "'";

        connect = database.connectDb();

        try {
            Alert alert;

            if (salary_employeeID.getText().isEmpty()
                    || salary_firstName.getText().isEmpty()
                    || salary_lastName.getText().isEmpty()
                    || salary_position.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select item first");
                alert.showAndWait();
            } else {
                statement = connect.createStatement();
                statement.executeUpdate(sql);

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Updated!");
                alert.showAndWait();

                salaryShowListData();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void salaryReset() {
        salary_employeeID.setText("");
        salary_firstName.setText("");
        salary_lastName.setText("");
        salary_position.setText("");
        salary_salary.setText("");
    }

    public ObservableList<employeeData> salaryListData() {

        ObservableList<employeeData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM employee_info";

        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            employeeData employeeD;

            while (result.next()) {
                employeeD = new employeeData(result.getInt("employee_id"),
                         result.getString("firstName"),
                         result.getString("lastName"),
                         result.getString("position"),
                         result.getDouble("salary"));

                listData.add(employeeD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<employeeData> salaryList;

    public void salaryShowListData() {
        salaryList = salaryListData();

        salary_col_employeeID.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        salary_col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        salary_col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        salary_col_position.setCellValueFactory(new PropertyValueFactory<>("position"));
        salary_col_salary.setCellValueFactory(new PropertyValueFactory<>("salary"));

        salary_tableView.setItems(salaryList);

    }

    @FXML
    public void salarySelect() {

        employeeData employeeD = salary_tableView.getSelectionModel().getSelectedItem();
        int num = salary_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        salary_employeeID.setText(String.valueOf(employeeD.getEmployeeId()));
        salary_firstName.setText(employeeD.getFirstName());
        salary_lastName.setText(employeeD.getLastName());
        salary_position.setText(employeeD.getPosition());
        salary_salary.setText(String.valueOf(employeeD.getSalary()));

    }

    public void defaultNav() {
        home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
    }

    public void displayUsername() {
        username.setText(getData.username);
    }

    @FXML
    public void switchForm(ActionEvent event) {

        if (event.getSource() == home_btn) {
            home_form.setVisible(true);
            addEmployee_form.setVisible(false);
            salary_form.setVisible(false);
            event_form.setVisible(false);
            leave_form.setVisible(false);

            leave_btn.setStyle("-fx-background-color:transparent");

            home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            addEmployee_btn.setStyle("-fx-background-color:transparent");
            salary_btn.setStyle("-fx-background-color:transparent");
            event_btn.setStyle("-fx-background-color:transparent");

            homeTotalEmployees();
            homeEmployeeTotalPresent();
            homeTotalInactive();
            homeChart();

        } else if (event.getSource() == addEmployee_btn) {
            home_form.setVisible(false);
            addEmployee_form.setVisible(true);
            salary_form.setVisible(false);
            event_form.setVisible(false);
            event_btn.setStyle("-fx-background-color:transparent");
            leave_form.setVisible(false);

            leave_btn.setStyle("-fx-background-color:transparent");
            addEmployee_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            home_btn.setStyle("-fx-background-color:transparent");
            salary_btn.setStyle("-fx-background-color:transparent");
            event_btn.setStyle("-fx-background-color:transparent");

            addEmployeeGendernList();
            addEmployeePositionList();
            addEmployeeSearch();

        } else if (event.getSource() == salary_btn) {
            home_form.setVisible(false);
            addEmployee_form.setVisible(false);
            salary_form.setVisible(true);
            event_form.setVisible(false);
            leave_form.setVisible(false);

            leave_btn.setStyle("-fx-background-color:transparent");
            salary_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            addEmployee_btn.setStyle("-fx-background-color:transparent");
            event_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");

            salaryShowListData();

        }else if (event.getSource() == event_btn) {
            home_form.setVisible(false);
            addEmployee_form.setVisible(false);
            salary_form.setVisible(false);
            event_form.setVisible(true);
            leave_form.setVisible(false);

            leave_btn.setStyle("-fx-background-color:transparent");
            event_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            addEmployee_btn.setStyle("-fx-background-color:transparent");

            home_btn.setStyle("-fx-background-color:transparent");
            salary_btn.setStyle("-fx-background-color:transparent");

            salaryShowListData();

        } else if (event.getSource() == leave_btn) {
            home_form.setVisible(false);
            addEmployee_form.setVisible(false);
            salary_form.setVisible(false);
            event_form.setVisible(false);
            leave_form.setVisible(true);

            leave_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            event_btn.setStyle("-fx-background-color:transparent");
            addEmployee_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");
            salary_btn.setStyle("-fx-background-color:transparent");
        }

    }




    @FXML
    void leaveSelect(MouseEvent event) {
        //
    }

    private double x = 0;
    private double y = 0;

    @FXML
    public void logout() {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");
        Optional<ButtonType> option = alert.showAndWait();
        try {
            if (option.get().equals(ButtonType.OK)) {

                logout.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(.8);
                });

                root.setOnMouseReleased((MouseEvent event) -> {
                    stage.setOpacity(1);
                });

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void close() {
        System.exit(0);
    }

    @FXML
    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }
     @FXML
    public void back(ActionEvent event) throws IOException
    {
        Parent view3=FXMLLoader.load(getClass().getResource("SecondaryController.fxml"));
                Scene scene3=new Scene(view3);
                Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene3);
               window.show();
    }
    
   @FXML
    private void handleManageBenefits(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Benefits.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Manage Benefits");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }   
    
    
     @FXML
    private void handleManageHiring() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminHiring.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Manage Hiring");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
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


    public ObservableList<LeaveRequest> addLeaveListManagementListData() {
        ObservableList<LeaveRequest> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM leave_request";
        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            LeaveRequest leaveRequest;

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

                leaveRequest.setUsername(getUsernameById(result.getInt("employee_id")));

                // Set the status string based on the status value
                if (leaveRequest.getStatus() == 0) {
                    leaveRequest.setSt("Rejected");
                } else if (leaveRequest.getStatus() == 1) {
                    leaveRequest.setSt("Pending");
                } else {
                    leaveRequest.setSt("Approved");
                }
                listData.add(leaveRequest);
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


    private ObservableList<LeaveRequest> addLeaveManagementList;


    private void addLeaveManagementShowListData() {
        addLeaveManagementList = addLeaveListManagementListData();

        // Set cell value factories for the TableView columns
        tcUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        tcStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        tcEndDt.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        tcDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tcNumDays.setCellValueFactory(new PropertyValueFactory<>("numDays"));
        tcStatus.setCellValueFactory(new PropertyValueFactory<>("st"));

        leaveTableView.setItems(addLeaveManagementList);
    }

    
    private void clearFields(){
        tfEventId.clear();
        tfDate.clear();
        tfEndDate.clear();
        tfLocation.clear();
        tfDesc.clear();
    }
    
    private void addEvent(){
        
        int eventId = Integer.parseInt(tfEventId.getText());
        String desc = tfDesc.getText();
        String date = tfDate.getText();
        String endDate = tfEndDate.getText();
        String loc = tfLocation.getText();
        
     
        String sql = "INSERT INTO Event (id, description, date, end_date, location) VALUES (? ,?, ?, ?, ?)";

        connect = database.connectDb();

        try {
            Alert alert;
            if (tfEventId.getText().isEmpty()
                    || tfDesc.getText().isEmpty()
                    || tfEndDate.getText().isEmpty()
                    || tfDate.getText().isEmpty()
                    || tfLocation.getText().isEmpty()
                    ) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message Yh");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {

                String check = "SELECT id FROM Event WHERE id = "+eventId;

                statement = connect.createStatement();
                result = statement.executeQuery(check);

                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Event ID: " + eventId + " was already exist!");
                    alert.showAndWait();
                } else {

                    prepare = connect.prepareStatement(sql);
                    prepare.setInt(1, eventId);
                    prepare.setString(2, desc);
                    prepare.setString(3, date);
                    prepare.setString(4, endDate);
                    prepare.setString(5, loc);
                   
                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();
  
                    addEventList.add(new eventData(eventId,desc,date,endDate,loc));
                    
                    
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        
    }

    private void updateEvent(){

        eventData eData = eventTableView.getSelectionModel().getSelectedItem();
        int oldId = eData.getId();

        int eventId = Integer.parseInt(tfEventId.getText());
        String desc = tfDesc.getText();
        String date = tfDate.getText();
        String endDate = tfEndDate.getText();
        String loc = tfLocation.getText();


        String sql = "UPDATE Event SET id = ?, description=?, date=?, end_date=?, location=? WHERE id="+oldId;

        connect = database.connectDb();
        try {
            Alert alert;
            if (tfEventId.getText().isEmpty()
                    || tfDesc.getText().isEmpty()
                    || tfEndDate.getText().isEmpty()
                    || tfDate.getText().isEmpty()
                    || tfLocation.getText().isEmpty()
            ) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message Yh");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                if(oldId == eventId){

                    String check = "SELECT id FROM Event WHERE id = "+oldId;

                    statement = connect.createStatement();
                    result = statement.executeQuery(check);

                    if (result.next()) {

                        prepare = connect.prepareStatement(sql);
                        prepare.setInt(1, oldId);
                        prepare.setString(2, desc);
                        prepare.setString(3, date);
                        prepare.setString(4, endDate);
                        prepare.setString(5, loc);

                        int row = prepare.executeUpdate();

                        if (row > 0){
                            alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Information Message");
                            alert.setHeaderText(null);
                            alert.setContentText("Successfully Updated!");
                            alert.showAndWait();

                            // Update the eventData object in the ObservableList
                            eData.setDescription(desc);
                            eData.setDate(date);
                            eData.setEndDate(endDate);
                            eData.setLocation(loc);

                            // Refresh the TableView
                            eventTableView.refresh();
                        }



                    } else {

                        alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Event ID: " + oldId + " not found to update in database");
                        alert.showAndWait();
                    }
                }else{

                    String check = "SELECT id FROM Event WHERE id = "+eventId;

                    statement = connect.createStatement();
                    result = statement.executeQuery(check);

                    if (result.next()) {
                        alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Event ID: " + eventId + " was already exist!");
                        alert.showAndWait();
                    }else{
                        prepare = connect.prepareStatement(sql);
                        prepare.setInt(1, eventId);
                        prepare.setString(2, desc);
                        prepare.setString(3, date);
                        prepare.setString(4, endDate);
                        prepare.setString(5, loc);

                        int row = prepare.executeUpdate();

                        if (row > 0){
                            alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Information Message");
                            alert.setHeaderText(null);
                            alert.setContentText("Successfully Updated!");
                            alert.showAndWait();

                            // Update the eventData object in the ObservableList
                            eData.setId(eventId);
                            eData.setDescription(desc);
                            eData.setDate(date);
                            eData.setEndDate(endDate);
                            eData.setLocation(loc);

                            // Refresh the TableView
                            eventTableView.refresh();
                        }

                    }


                }






            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


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

    private void approveLeave(){

        LeaveRequest leaveRequest = leaveTableView.getSelectionModel().getSelectedItem();
        int requestId = leaveRequest.getId();

        String sql = "UPDATE leave_request SET status = ? WHERE id="+requestId;

        connect = database.connectDb();
        try {
            Alert alert;

            if(leaveRequest.getStatus() == 2){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Leave already approved");
                alert.showAndWait();
            }else{
                prepare = connect.prepareStatement(sql);
                prepare.setInt(1, 2);
                int row = prepare.executeUpdate();

                if (row > 0){
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully approved!");
                    alert.showAndWait();

                    leaveRequest.setSt("Approved");
                    leaveRequest.setStatus((short) 2);

                    // Refresh the TableView
                    leaveTableView.refresh();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void rejectLeave(){

        LeaveRequest leaveRequest = leaveTableView.getSelectionModel().getSelectedItem();
        int requestId = leaveRequest.getId();

        String sql = "UPDATE leave_request SET status = ? WHERE id="+requestId;

        connect = database.connectDb();
        try {
            Alert alert;

            if(leaveRequest.getStatus() == 0){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Leave already rejected");
                alert.showAndWait();
            }else{
                prepare = connect.prepareStatement(sql);
                prepare.setInt(1, 0);
                int row = prepare.executeUpdate();

                if (row > 0){
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully rejected!");
                    alert.showAndWait();

                    leaveRequest.setSt("Rejected");
                    leaveRequest.setStatus((short) 0);

                    // Refresh the TableView
                    leaveTableView.refresh();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void deleteEvent() throws SQLException {

        Alert alert;
        connect = database.connectDb();

        if (tfEventId.getText().isEmpty()) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message Yh");
            alert.setHeaderText(null);
            alert.setContentText("Event Id must not be empty");
            alert.showAndWait();
        }else{
            int id = Integer.parseInt(tfEventId.getText());
            String sql = "DELETE FROM Event WHERE id="+id;
            prepare = connect.prepareStatement(sql);


            int row = prepare.executeUpdate();


            if(row > 0){
                eventData eData = eventTableView.getSelectionModel().getSelectedItem();
                addEventList.remove(eData);
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Removed!");
                alert.showAndWait();
                clearFields();
            }

        }

    }

    // Method to get the username from the user table using the id
    public String getUsernameById(int id) {

        // SQL query to get the username using the id
        String sql = "SELECT username FROM user WHERE id = ?";


        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Establish the connection to the database
            connect = database.connectDb();

            // Prepare the SQL statement
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            // Execute the query
            resultSet = preparedStatement.executeQuery();

            // Check if a result is returned and retrieve the username
            if (resultSet.next()) {
                return resultSet.getString("username");
            } else {
                // Return null or handle the case where the id is not found
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            // Close the resources in the finally block to ensure they are closed
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connect != null) connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayUsername();
        defaultNav();
        employeeType();

        homeTotalEmployees();
        homeEmployeeTotalPresent();
        homeTotalInactive();
        homeChart();

        addEmployeeShowListData();
        addEventShowListData();
        addLeaveManagementShowListData();
        addEmployeeGendernList();
        addEmployeePositionList();
        
         // Create a filter to accept only numeric input
        Pattern pattern = Pattern.compile("\\d*");
        TextFormatter<String> textFormatter = new TextFormatter<>((UnaryOperator<TextFormatter.Change>) change -> {
            if (pattern.matcher(change.getControlNewText()).matches()) {
                return change;
            } else {
                return null;
            }
        });

        tfEventId.setTextFormatter(textFormatter);
        
        
        evClear.setOnAction(e ->{
            clearFields();
        });
        
        evAdd.setOnAction(e ->{
            addEvent();
        });

        evDel.setOnAction(e ->{
            try {
                deleteEvent();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        evUpdate.setOnAction(e ->{
            updateEvent();
        });

        lApprove.setOnAction(e ->{
            approveLeave();
        });

        lReject.setOnAction(e ->{
            rejectLeave();
        });

        salaryShowListData();
    }




}
