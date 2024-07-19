package com.mycompany.employeemanagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.scene.control.cell.PropertyValueFactory;

public class BenefitsController implements Initializable {

    @FXML
    private TableView<Benefit> benefitsTableView;

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

    @FXML
    private Button addBtn;

    @FXML
    private Button updateBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button clearBtn;

    @FXML
    private GridPane benefitDialog;

    @FXML
    private ComboBox<String> benefitTypeField;

    @FXML
    private TextField benefitNameField;

    @FXML
    private TextField benefitDescriptionField;

    @FXML
    private DatePicker benefitDateField;

    @FXML
    private TextField benefitAmountField;

    @FXML
    private Button okBtn;

    @FXML
    private Button cancelBtn;

    private ObservableList<Benefit> benefitList = FXCollections.observableArrayList();
    private Benefit currentBenefit;
    private boolean isEditing;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadBenefitData();
        benefitDialog.setVisible(true);
        benefitTypeField.setItems(FXCollections.observableArrayList("Full-Time", "Part-Time", "Both"));
    }

    public void loadBenefitData() {
        try {
            Connection conn = database.connectDb();
            String query = "SELECT * FROM benefits";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Benefit benefit = new Benefit(
                        rs.getInt("employee_id"),
                        rs.getString("employee_type"),
                        rs.getString("benefit_name"),
                        rs.getString("benefit_description"),
                        rs.getDate("benefit_date"),
                        rs.getDouble("benefit_amount")
                );
                benefitList.add(benefit);
                System.out.println("Loaded Benefit: " + benefit.getBenefitName()); // Print to verify data
            }

            tcBenefitId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
            tcBenefitType.setCellValueFactory(new PropertyValueFactory<>("employeeType"));
            tcBenefitName.setCellValueFactory(new PropertyValueFactory<>("benefitName"));
            tcBenefitDesc.setCellValueFactory(new PropertyValueFactory<>("benefitDescription"));
            tcBenefitDate.setCellValueFactory(new PropertyValueFactory<>("benefitDate"));
            tcBenefitAmount.setCellValueFactory(new PropertyValueFactory<>("benefitAmount"));

            benefitsTableView.setItems(benefitList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddBenefit() {
        currentBenefit = new Benefit();
        isEditing = false;
        System.out.println("Add button clicked");
        showDialog();
    }

    @FXML
    private void handleUpdateBenefit() {
        currentBenefit = benefitsTableView.getSelectionModel().getSelectedItem();
        if (currentBenefit != null) {
            isEditing = true;
            System.out.println("Update button clicked");
            showDialog();
        } else {
            showAlert("No Selection", "No Benefit Selected", "Please select a benefit in the table.");
        }
    }

    @FXML
    private void handleDeleteBenefit() {
        Benefit selectedBenefit = benefitsTableView.getSelectionModel().getSelectedItem();
        if (selectedBenefit != null) {
            try {
                Connection conn = database.connectDb();
                String query = "DELETE FROM benefits WHERE employee_id = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setInt(1, selectedBenefit.getEmployeeId());
                stmt.executeUpdate();
                benefitList.remove(selectedBenefit);
                System.out.println("Deleted Benefit: " + selectedBenefit.getBenefitName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            showAlert("No Selection", "No Benefit Selected", "Please select a benefit in the table.");
        }
    }

    @FXML
    private void handleClearFields() {
        benefitsTableView.getSelectionModel().clearSelection();
    }

    @FXML
    private void handleOk() {
        System.out.println("OK button clicked");
        if (isInputValid()) {
            currentBenefit.setEmployeeType(benefitTypeField.getValue());
            currentBenefit.setBenefitName(benefitNameField.getText());
            currentBenefit.setBenefitDescription(benefitDescriptionField.getText());
            currentBenefit.setBenefitDate(java.sql.Date.valueOf(benefitDateField.getValue()));
            currentBenefit.setBenefitAmount(Double.parseDouble(benefitAmountField.getText()));

            if (isEditing) {
                System.out.println("Updating Benefit: " + currentBenefit.getBenefitName());
                updateBenefitInDatabase(currentBenefit);
            } else {
                System.out.println("Adding Benefit: " + currentBenefit.getBenefitName());
                addBenefitToDatabase(currentBenefit);
            }
            hideDialog();
        }
    }

    @FXML
    private void handleCancel() {
        System.out.println("Cancel button clicked");
        hideDialog();
    }

    private void showDialog() {
        if (isEditing) {
            benefitTypeField.setValue(currentBenefit.getEmployeeType());
            benefitNameField.setText(currentBenefit.getBenefitName());
            benefitDescriptionField.setText(currentBenefit.getBenefitDescription());
            benefitDateField.setValue(currentBenefit.getBenefitDate().toLocalDate());
            benefitAmountField.setText(String.valueOf(currentBenefit.getBenefitAmount()));
        } else {
            benefitTypeField.setValue(null);
            benefitNameField.clear();
            benefitDescriptionField.clear();
            benefitDateField.setValue(null);
            benefitAmountField.clear();
        }
        benefitDialog.setVisible(true);
    }

    private void hideDialog() {
        benefitDialog.setVisible(false);
        handleClearFields();
    }

    private void addBenefitToDatabase(Benefit benefit) {
        try {
            Connection conn = database.connectDb();
            String query = "INSERT INTO benefits (employee_id, employee_type, benefit_name, benefit_description, benefit_date, benefit_amount) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, benefit.getEmployeeId());
            stmt.setString(2, benefit.getEmployeeType());
            stmt.setString(3, benefit.getBenefitName());
            stmt.setString(4, benefit.getBenefitDescription());
            stmt.setDate(5, java.sql.Date.valueOf(benefit.getBenefitDate().toLocalDate()));
            stmt.setDouble(6, benefit.getBenefitAmount());
            stmt.executeUpdate();
            benefitList.add(benefit);
            System.out.println("Benefit added to database: " + benefit.getBenefitName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateBenefitInDatabase(Benefit benefit) {
        try {
            Connection conn = database.connectDb();
            String query = "UPDATE benefits SET employee_type = ?, benefit_name = ?, benefit_description = ?, benefit_date = ?, benefit_amount = ? WHERE employee_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, benefit.getEmployeeType());
            stmt.setString(2, benefit.getBenefitName());
            stmt.setString(3, benefit.getBenefitDescription());
            stmt.setDate(4, java.sql.Date.valueOf(benefit.getBenefitDate().toLocalDate()));
            stmt.setDouble(5, benefit.getBenefitAmount());
            stmt.setInt(6, benefit.getEmployeeId());
            stmt.executeUpdate();
            benefitsTableView.refresh();
            System.out.println("Benefit updated in database: " + benefit.getBenefitName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (benefitTypeField.getValue() == null || benefitTypeField.getValue().isEmpty()) {
            errorMessage += "No valid employee type!\n";
        }
        if (benefitNameField.getText() == null || benefitNameField.getText().isEmpty()) {
            errorMessage += "No valid benefit name!\n";
        }
        if (benefitDescriptionField.getText() == null || benefitDescriptionField.getText().isEmpty()) {
            errorMessage += "No valid benefit description!\n";
        }
        if (benefitDateField.getValue() == null) {
            errorMessage += "No valid benefit date!\n";
        }
        if (benefitAmountField.getText() == null || benefitAmountField.getText().isEmpty()) {
            errorMessage += "No valid benefit amount!\n";
        } else {
            try {
                Double.parseDouble(benefitAmountField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid benefit amount (must be a number)!\n";
            }
        }

        if (errorMessage.isEmpty()) {
            return true;
        } else {
            showAlert("Invalid Fields", "Please correct invalid fields", errorMessage);
            return false;
        }
    }

    private void showAlert(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}
