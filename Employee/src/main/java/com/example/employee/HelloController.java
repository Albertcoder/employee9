package com.example.employee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloController {
    @FXML private TextField nameField;
    @FXML private TextField positionField;
    @FXML private TextField salaryField;
    @FXML private TextField hireDateField;

    @FXML private TableView<Employee> employeeTable;
    @FXML private TableColumn<Employee, Integer> idColumn;
    @FXML private TableColumn<Employee, String> nameColumn;
    @FXML private TableColumn<Employee, String> positionColumn;
    @FXML private TableColumn<Employee, Double> salaryColumn;
    @FXML private TableColumn<Employee, Date> hireDateColumn;

    private final EmployeeData employeeData = new EmployeeData();
    private final ObservableList<Employee> employeeList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        hireDateColumn.setCellValueFactory(new PropertyValueFactory<>("hireDate"));

        loadEmployees();
    }

    @FXML
    private void handleAddEmployee() {
        try {
            String name = nameField.getText();
            String position = positionField.getText();
            double salary = Double.parseDouble(salaryField.getText());
            Date hireDate = new SimpleDateFormat("yyyy-MM-dd").parse(hireDateField.getText());

            Employee employee = new Employee(name, position, salary, hireDate);
            employeeData.createEmployee(employee);
            loadEmployees();
            clearFields();
        } catch (Exception e) {
            showError("Invalid input. Please check your fields.");
        }
    }

    @FXML
    private void handleUpdateEmployee() {
        Employee selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
        if (selectedEmployee != null) {
            try {
                selectedEmployee.setName(nameField.getText());
                selectedEmployee.setPosition(positionField.getText());
                selectedEmployee.setSalary(Double.parseDouble(salaryField.getText()));
                selectedEmployee.setHireDate(new SimpleDateFormat("yyyy-MM-dd").parse(hireDateField.getText()));

                employeeData.updateEmployee(selectedEmployee);
                loadEmployees();
                clearFields();
            } catch (Exception e) {
                showError("Invalid input. Please check your fields.");
            }
        } else {
            showError("No employee selected.");
        }
    }

    @FXML
    private void handleDeleteEmployee() {
        Employee selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
        if (selectedEmployee != null) {
            employeeData.deleteEmployee(selectedEmployee.getId());
            loadEmployees();
            clearFields();
        } else {
            showError("No employee selected.");
        }
    }

    @FXML
    private void clearFields() {
        nameField.clear();
        positionField.clear();
        salaryField.clear();
        hireDateField.clear();
        employeeTable.getSelectionModel().clearSelection();
    }

    private void loadEmployees() {
        employeeList.setAll(employeeData.getAllEmployees());
        employeeTable.setItems(employeeList);
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
