package home.controllers;

import home.constants.Session;
import home.database.StudentDao;
import home.model.Student;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StudentsController implements Initializable, javafx.beans.value.ChangeListener<String> {

    @FXML
    private Label studentEmail;
    @FXML
    private TableView<Student> tableView;
    @FXML
    public TableColumn<Student, Integer> studentRoll;
    @FXML
    public TableColumn<Student, String> studentName;
    @FXML
    public TableColumn<Student, String> studentDepartment;
    @FXML
    private TextField searchView;
    @FXML
    private Pane studentPane;
    @FXML
    private TextField deptTxt;
    @FXML
    private TextField nameTxt;
    @FXML
    private TextField rollTxt;
    @FXML
    private Label sessionTxt;
    @FXML
    private Button updateBtn;
    @FXML
    private Button deleteBtn;

    private Student student;

    private ObservableList<Student> studentList;
    private ObservableList<Student> filteredList;

    public StudentsController() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        studentRoll.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getStudentRoll()));
        studentName.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getStudentName()));
        studentDepartment.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getStudentDepartment()));

        if (Session.user != null) {

            if (Session.user.getIsAdmin() == 0) {

                updateBtn.setVisible(false);
                deleteBtn.setVisible(false);

                nameTxt.setEditable(false);
                rollTxt.setEditable(false);
                deptTxt.setEditable(false);
            }
        }

        try {

            filteredList = StudentDao.getStudentList();
            studentList = FXCollections.observableArrayList();
            studentList.addAll(filteredList);

        } catch (Exception e) {

            e.printStackTrace();
        }

        searchView.textProperty().addListener(this);
        tableView.setItems(filteredList);
        studentPane.setVisible(false);

        tableView.setRowFactory(param -> {

            TableRow<Student> row = new TableRow<>();

            row.setOnMouseClicked(event -> {

                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY) {

                    Student student = row.getItem();
                    updatePane(student);

                } else {

                    studentPane.setVisible(false);
                    this.student = null;
                }
            });

            return row;
        });
    }

    private void updatePane(Student student) {

        studentPane.setVisible(true);

        this.student = student;

        nameTxt.setText(student.getStudentName());
        rollTxt.setText(String.valueOf(student.getStudentRoll()));
        deptTxt.setText(student.getStudentDepartment());
        sessionTxt.setText(student.getStudentSession());
        studentEmail.setText(student.getStudentEmail());
    }

    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

        try {

            searchByName(studentList, newValue);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @FXML
    void deleteStudent(ActionEvent event) {

        if (this.student != null) {

            try {

                StudentDao.deleteStudent(String.valueOf(student.getStudentId()));

                filteredList.clear();
                studentList.clear();
                filteredList.addAll(StudentDao.getStudentList());
                studentList.addAll(filteredList);

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }

    @FXML
    void updateStudent(ActionEvent event) {

        if (this.student != null) {

            try {

                if (nameTxt.getText() != null && !nameTxt.getText().isEmpty()) {

                    if (rollTxt.getText() != null && !rollTxt.getText().isEmpty()) {

                        if (deptTxt.getText() != null && !deptTxt.getText().isEmpty()) {

                            this.student.setStudentName(nameTxt.getText());
                            this.student.setStudentRoll(Integer.parseInt(rollTxt.getText()));
                            this.student.setStudentDepartment(deptTxt.getText());

                            StudentDao.updateStudent(student);

                            filteredList.clear();
                            studentList.clear();
                            filteredList.addAll(StudentDao.getStudentList());
                            studentList.addAll(filteredList);

                            return;
                        }
                    }
                }

                Alert alert = new Alert (Alert.AlertType.WARNING);
                alert.setTitle("Invalid Information");
                alert.setHeaderText("One or multiple fields are empty!");
                alert.setContentText("Please recheck the information.");
                alert.show();

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }

    private void searchByName(ObservableList<Student> studentList, String name) {

        filteredList.clear();

        if (name.length() == 0) {

            filteredList.addAll(studentList);

        } else {

            for (Student student : studentList) {

                if (student.getStudentName().toLowerCase().contains(name.toLowerCase())) {

                    filteredList.add(student);
                }
            }
        }
    }
}
