package home.controllers;

import home.constants.Session;
import home.database.LoginDao;
import home.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField emailTxt;
    @FXML
    private PasswordField passwordTxt;
    @FXML
    private RadioButton studentRadio;
    @FXML
    private RadioButton facultyRadio;
    @FXML
    private Button loginBtn;

    private ToggleGroup group;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initRadioButtons();
    }

    private void initRadioButtons() {

        group = new ToggleGroup();

        studentRadio.setToggleGroup(group);
        facultyRadio.setToggleGroup(group);

        studentRadio.setSelected(true);
    }

    @FXML
    public void onLoginClick(ActionEvent event) {

        if (emailTxt.getText().isEmpty() || passwordTxt.getText().isEmpty()) {

            Alert alert = new Alert (Alert.AlertType.WARNING);
            alert.setTitle("Missing Information");
            alert.setHeaderText("One or multiple fields are empty!");
            alert.setContentText("Please fill up the fields.");
            alert.show();

        } else {

            try {

                RadioButton button = (RadioButton) group.getSelectedToggle();
                int isAdmin;

                if (button == studentRadio) {

                    isAdmin = 0;

                } else {

                    isAdmin = 1;
                }

                User user = LoginDao.loginUser(emailTxt.getText(), passwordTxt.getText(), isAdmin);

                if (user != null) {

                    Session.user = user;

                    loginBtn.getScene().getWindow().hide();
                    loadStage();

                } else {

                    Alert alert = new Alert (Alert.AlertType.WARNING);
                    alert.setTitle("Invalid Information");
                    alert.setHeaderText("User not found!");
                    alert.setContentText("Please check your information.");
                    alert.show();
                }

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }

    private void loadStage() {

        try {

            Parent root = FXMLLoader.load(getClass().getResource("/home/fxml/Home.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.getIcons().add(new Image("/home/icons/icon.png"));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public void onKeyPressed(KeyEvent event) {

        if (event.getCode() == KeyCode.ENTER) {

            if (event.getSource() == passwordTxt) {

                loginBtn.fire();

            } else if (event.getSource() == emailTxt) {

                passwordTxt.requestFocus();
            }
        }
    }
}
