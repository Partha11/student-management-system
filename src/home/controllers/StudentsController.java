package home.controllers;

import home.model.StudentsModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentsController implements Initializable {

    @FXML
    private TableView<StudentsModel> tableView;
    @FXML
    public TableColumn<StudentsModel, Integer> studentId;
    @FXML
    public TableColumn<StudentsModel, String> firstName;
    @FXML
    public TableColumn<StudentsModel, String> lastName;

    private ObservableList<StudentsModel> studentList;

    public StudentsController() {

        //Empty Controller
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        studentId.setCellValueFactory(new PropertyValueFactory<>("StudentId"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        tableView.setItems(studentList);
    }

    public void setStudentList(ObservableList<StudentsModel> studentList) {

        this.studentList = studentList;
    }

    private ObservableList<StudentsModel> studentsModels = FXCollections.observableArrayList(

            new StudentsModel(1,"Amos", "Chepchieng"),
            new StudentsModel(2,"Amos", "Mors"),
            new StudentsModel(3,"Amos", "Chepchieng"),
            new StudentsModel(3,"Amos", "Chepchieng"),
            new StudentsModel(3,"Amos", "Chepchieng"),
            new StudentsModel(3,"Amos", "Chepchieng"),
            new StudentsModel(3,"Amos", "Chepchieng"),
            new StudentsModel(3,"Amos", "Chepchieng"),
            new StudentsModel(3,"Amos", "Chepchieng"),
            new StudentsModel(3,"Amos", "Chepchieng"),
            new StudentsModel(3,"Amos", "Chepchieng"),
            new StudentsModel(3,"Amos", "Chepchieng"),
            new StudentsModel(3,"Amos", "Chepchieng"),
            new StudentsModel(3,"Amos", "Chepchieng"),
            new StudentsModel(3,"Amos", "Chepchieng"),
            new StudentsModel(3,"Amos", "Chepchieng"),
            new StudentsModel(3,"Amos", "Chepchieng"),
            new StudentsModel(3,"Amos", "Chepchieng"),
            new StudentsModel(3,"Amos", "Chepchieng"),
            new StudentsModel(3,"Amos", "Chepchieng"),
            new StudentsModel(3,"Amos", "Chepchieng"),
            new StudentsModel(3,"Amos", "Chepchieng"),
            new StudentsModel(3,"Amos", "Chepchieng"),
            new StudentsModel(3,"Amos", "Chepchieng"),
            new StudentsModel(3,"Amos", "Chepchieng"),
            new StudentsModel(3,"Amos", "Chepchieng"),
            new StudentsModel(3,"Amos", "Chepchieng"),
            new StudentsModel(3,"Amos", "Chepchieng"),
            new StudentsModel(3,"Amos", "Chepchieng"),
            new StudentsModel(4,"Amos", "Mors")
    );
}
