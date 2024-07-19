/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.employeemanagement;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Gagandeep Singh
 */
public class WelcomePageController implements Initializable {

    @FXML
    private Button logoutBtn;

    @FXML
    private Button leaveBtn;

    @FXML
    private Button eventsBtn;
    
    @FXML
private void handleViewBenefits(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserBenefits.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("My Benefits");
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    @FXML
    private void handleViewHiring() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UserHiring.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("View Hiring");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

       
    // Logout method click listener
    @FXML
    void logout(ActionEvent event) throws IOException {

        Alert alert =new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirm logout");
        alert.setHeaderText(null);
        alert.setContentText("Continue logging out?");
        Optional <ButtonType> action =alert.showAndWait();
        
        if (action.get()==ButtonType.OK){
        Parent view5=FXMLLoader.load(getClass().getResource("userLogin.fxml"));
                Scene scene5=new Scene(view5);
                Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene5);
                window.show();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        System.out.println();
        eventsBtn.setOnAction(e ->{

            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("events_user.fxml"));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Events");
            stage.setScene(scene);


            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(eventsBtn.getScene().getWindow());
            stage.show();

        });


        leaveBtn.setOnAction(e ->{

            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("leave_management_user.fxml"));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Leave Request Management");
            stage.setScene(scene);


            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(leaveBtn.getScene().getWindow());
            stage.show();

        });
    }

    
}
