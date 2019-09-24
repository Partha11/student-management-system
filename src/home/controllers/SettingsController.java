package home.controllers;

import home.constants.Session;
import home.database.LoginDao;
import home.database.StudentDao;
import home.model.Student;
import home.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {

    @FXML
    private TextField adminName;
    @FXML
    private TextField adminEmail;
    @FXML
    private PasswordField adminNewPassword;
    @FXML
    private PasswordField adminOldPassword;
    @FXML
    private Button updateBtn;
    @FXML
    private PasswordField studentPassword;
    @FXML
    private TextField studentName;
    @FXML
    private TextField studentRoll;
    @FXML
    private TextField studentSession;
    @FXML
    private TextField studentEmail;
    @FXML
    private ComboBox<String> studentDept;
    @FXML
    private Button insertBtn;

    @FXML
    void onButtonClicked(ActionEvent event) {

        if (event.getSource() == updateBtn) {

            String name = adminName.getText();
            String email = adminEmail.getText();
            String oldPassword = adminOldPassword.getText();
            String newPassword = adminNewPassword.getText();

            if (!name.isEmpty()) {

                try {

                    LoginDao.updateName(name, Session.user.getId());

                } catch (Exception e) {

                    e.printStackTrace();
                }
            }

            if (!email.isEmpty()) {

                try {

                    LoginDao.updateEmail(email, Session.user.getId());

                } catch (Exception e) {

                    e.printStackTrace();
                }
            }

            if (!oldPassword.isEmpty()) {

                try {

                    User user = LoginDao.loginUser(Session.user.getEmail(), oldPassword, 1);

                    if (user != null && user.getIsAdmin() == 1) {

                        LoginDao.updatePassword(newPassword, Session.user.getId());
                    }

                } catch (Exception e) {

                    e.printStackTrace();
                }
            }

        } else if (event.getSource() == insertBtn) {

            String name = studentName.getText();
            String email = studentEmail.getText();
            String roll = studentRoll.getText();
            String session = studentSession.getText();
            String dept = studentDept.getValue();
            String password = studentPassword.getText();

            if (!name.isEmpty() && !email.isEmpty() && !roll.isEmpty() &&
                    !session.isEmpty() && !dept.isEmpty()) {

                Student student = new Student();
                User user = new User();

                student.setStudentName(name);
                student.setStudentEmail(email);
                student.setStudentRoll(Integer.parseInt(roll));
                student.setStudentDepartment(dept);
                student.setStudentSession(session);

                user.setName(name);
                user.setEmail(email);
                user.setIsAdmin(0);

                try {

                    StudentDao.insertStudent(student);
                    LoginDao.insertUser(user, password);

                    studentName.setText("");
                    studentEmail.setText("");
                    studentPassword.setText("");
                    studentRoll.setText("");
                    studentSession.setText("");
                    studentDept.getSelectionModel().selectFirst();

                } catch (Exception ex) {

                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setFields();
    }

    private void setFields() {

        if (Session.user != null) {

            adminName.setText(Session.user.getName());
            adminEmail.setText(Session.user.getEmail());
        }

        ObservableList<String> list = FXCollections.observableArrayList();

        list.add("CSE");
        list.add("IT");
        list.add("Textile");
        list.add("BBA");
        list.add("English Literature");
        list.add("Environmental Science");
        list.add("Nutrition and Food Science");
        list.add("Public Health");

        studentDept.setItems(list);
        studentDept.getSelectionModel().selectFirst();
    }
}
