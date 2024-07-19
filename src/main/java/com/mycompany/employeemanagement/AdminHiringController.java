package com.mycompany.employeemanagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class AdminHiringController implements Initializable {

    @FXML
    private TableView<UserApplication> userApplicationsTableView;

    @FXML
    private TableColumn<UserApplication, Integer> tcUserId;

    @FXML
    private TableColumn<UserApplication, String> tcUserName;

    @FXML
    private TableColumn<UserApplication, String> tcApplicationStatus;

    @FXML
    private TableView<HiringPost> hiringPostTableView;

    @FXML
    private TableColumn<HiringPost, Integer> tcHiringPostId;

    @FXML
    private TableColumn<HiringPost, String> tcTitle;

    @FXML
    private TableColumn<HiringPost, String> tcDescription;

    @FXML
    private TableColumn<HiringPost, java.sql.Date> tcCreatedAt;

    @FXML
    private TextField titleField;

    @FXML
    private TextField descriptionField;

    @FXML
    private Button addBtn, updateBtn, deleteBtn;

    private ObservableList<UserApplication> userApplicationList = FXCollections.observableArrayList();
    private ObservableList<HiringPost> hiringPostList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadHiringPosts();
        tcHiringPostId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tcDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tcCreatedAt.setCellValueFactory(new PropertyValueFactory<>("createdAt"));

        tcUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        tcUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        tcApplicationStatus.setCellValueFactory(new PropertyValueFactory<>("applicationStatus"));
    }

    public void loadUserApplications(int hiringPostId) {
        userApplicationList.clear();
        try {
            Connection conn = database.connectDb();
            String query = "SELECT ja.user_id, u.name, ja.application_status " +
                           "FROM job_applications ja " +
                           "JOIN user u ON ja.user_id = u.id " +
                           "WHERE ja.hiring_post_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, hiringPostId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                UserApplication application = new UserApplication(
                        rs.getInt("user_id"),
                        rs.getString("name"),
                        rs.getString("application_status")
                );
                userApplicationList.add(application);
            }
            userApplicationsTableView.setItems(userApplicationList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleJobPostSelect() {
        HiringPost selectedPost = hiringPostTableView.getSelectionModel().getSelectedItem();
        if (selectedPost != null) {
            loadUserApplications(selectedPost.getId());
        }
    }

    public void loadHiringPosts() {
        hiringPostList.clear();
        try {
            Connection conn = database.connectDb();
            String query = "SELECT * FROM hiring_posts";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                HiringPost hiringPost = new HiringPost(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getDate("created_at")
                );
                hiringPostList.add(hiringPost);
            }

            tcHiringPostId.setCellValueFactory(new PropertyValueFactory<>("id"));
            tcTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
            tcDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
            tcCreatedAt.setCellValueFactory(new PropertyValueFactory<>("createdAt"));

            hiringPostTableView.setItems(hiringPostList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleAdd() {
        try {
            Connection conn = database.connectDb();
            String query = "INSERT INTO hiring_posts (title, description, created_at) VALUES (?, ?, NOW())";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, titleField.getText());
            pstmt.setString(2, descriptionField.getText());
            pstmt.executeUpdate();
            loadHiringPosts();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleUpdate() {
        // Update implementation
    }

    @FXML
    public void handleDelete() {
        // Delete implementation
    }
}
