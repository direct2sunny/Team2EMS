package com.mycompany.employeemanagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class UserBenefitsController implements Initializable {

    @FXML
    private TableView<Benefit> userBenefitsTableView;

    @FXML
    private TableColumn<Benefit, Integer> tcBenefitId;

    @FXML
    private TableColumn<Benefit, String> tcBenefitType;

    @FXML
    private TableColumn<Benefit, String> tcBenefitName;

    @FXML
    private TableColumn<Benefit, String> tcBenefitDesc;

    @FXML
    private TableColumn<Benefit, String> tcBenefitDate;

    @FXML
    private TableColumn<Benefit, Double> tcBenefitAmount;

    private ObservableList<Benefit> userBenefitList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadUserBenefitsData();
    }

    public void loadUserBenefitsData() {
        try {
            Connection conn = database.connectDb();
            String query = "SELECT * FROM benefits WHERE employee_id = ? OR employee_type = 'Both'"; // Adjust this query
            PreparedStatement stmt = conn.prepareStatement(query);
            // Set the employee_id parameter, assuming it's available. For example:
            int employeeId = 1; // Replace with actual employee ID
            stmt.setInt(1, employeeId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Benefit benefit = new Benefit(
                        rs.getInt("employee_id"),
                        rs.getString("employee_type"),
                        rs.getString("benefit_name"),
                        rs.getString("benefit_description"),
                        rs.getDate("benefit_date"),
                        rs.getDouble("benefit_amount")
                );
                userBenefitList.add(benefit);
                System.out.println("Benefit: " + benefit.getBenefitName()); // Debug print statement
            }

            tcBenefitId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
            tcBenefitType.setCellValueFactory(new PropertyValueFactory<>("employeeType"));
            tcBenefitName.setCellValueFactory(new PropertyValueFactory<>("benefitName"));
            tcBenefitDesc.setCellValueFactory(new PropertyValueFactory<>("benefitDescription"));
            tcBenefitDate.setCellValueFactory(new PropertyValueFactory<>("benefitDate"));
            tcBenefitAmount.setCellValueFactory(new PropertyValueFactory<>("benefitAmount"));

            userBenefitsTableView.setItems(userBenefitList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
