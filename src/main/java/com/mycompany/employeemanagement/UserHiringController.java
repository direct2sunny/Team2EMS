package com.mycompany.employeemanagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class UserHiringController implements Initializable {

    @FXML
    private TableView<HiringPost> userHiringTableView;

    @FXML
    private TableColumn<HiringPost, Integer> tcUserHiringPostId;

    @FXML
    private TableColumn<HiringPost, String> tcUserTitle;

    @FXML
    private TableColumn<HiringPost, String> tcUserDescription;

    @FXML
    private TableColumn<HiringPost, java.sql.Date> tcUserCreatedAt;

    @FXML
    private Button applyBtn, rejectBtn;

    private ObservableList<HiringPost> userHiringList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadUserHiringPosts();
    }

    public void loadUserHiringPosts() {
        userHiringList.clear();
        try {
            Connection conn = database.connectDb();
            String query = "SELECT * FROM hiring_posts";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                HiringPost hiringPost = new HiringPost(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getDate("created_at")
                );
                userHiringList.add(hiringPost);
            }

            tcUserHiringPostId.setCellValueFactory(new PropertyValueFactory<>("id"));
            tcUserTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
            tcUserDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
            tcUserCreatedAt.setCellValueFactory(new PropertyValueFactory<>("createdAt"));

            userHiringTableView.setItems(userHiringList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleApply() {
        HiringPost selectedPost = userHiringTableView.getSelectionModel().getSelectedItem();
        if (selectedPost != null) {
            try {
                Connection conn = database.connectDb();
                String query = "INSERT INTO job_applications (hiring_post_id, user_id, application_status, application_date) VALUES (?, ?, 'applied', NOW())";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, selectedPost.getId());
                pstmt.setInt(2, getCurrentUserId()); // Implement this method to get current user ID
                pstmt.executeUpdate();

                // Show success message
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Application Status");
                alert.setHeaderText(null);
                alert.setContentText("You have successfully applied for the job!");
                alert.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void handleReject() {
        HiringPost selectedPost = userHiringTableView.getSelectionModel().getSelectedItem();
        if (selectedPost != null) {
            try {
                Connection conn = database.connectDb();
                String query = "INSERT INTO job_applications (hiring_post_id, user_id, application_status, application_date) VALUES (?, ?, 'rejected', NOW())";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, selectedPost.getId());
                pstmt.setInt(2, getCurrentUserId()); // Implement this method to get current user ID
                pstmt.executeUpdate();

                // Show success message
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Application Status");
                alert.setHeaderText(null);
                alert.setContentText("You have successfully rejected the job!");
                alert.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private int getCurrentUserId() {
        // Implement logic to retrieve current user ID
        return 1; // Placeholder
    }
}
