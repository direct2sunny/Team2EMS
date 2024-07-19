module com.mycompany.employeemanagement {
    requires javafx.controls;
    requires javafx.fxml;
     requires java.sql;
    requires java.base;


    opens com.mycompany.employeemanagement to javafx.fxml;
    exports com.mycompany.employeemanagement;
}
