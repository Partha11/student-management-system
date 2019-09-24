package home.controllers;

import home.constants.Session;
import home.database.NoticeDao;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Label subtitle;
    @FXML
    private Button btnDashboard;
    @FXML
    private Button btnStudents;
    @FXML
    private Button btn_Timetable;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnClasses;

    @FXML
    private void handleButtonClicks(ActionEvent mouseEvent) {

        if (mouseEvent.getSource() == btnDashboard) {

            loadStage("/home/fxml/Dashboard.fxml");

        } else if (mouseEvent.getSource() == btnStudents) {

            loadStage("/home/fxml/Students.fxml");

        } else if (mouseEvent.getSource() == btn_Timetable) {

            loadStage("/home/fxml/Timetable.fxml");

        } else if (mouseEvent.getSource() == btnUpdate) {

            addNewNoticeDialog();

        } else if (mouseEvent.getSource() == btnClasses) {

            loadStage("/home/fxml/Routines.fxml");

        } else if (mouseEvent.getSource() == btnSettings) {

            loadStage("/home/fxml/Settings.fxml");
        }
    }

    private void addNewNoticeDialog() {

        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Add New Notice");

        ButtonType loginButtonType = new ButtonType("Add Notice", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField title = new TextField();
        title.setPromptText("Title");
        TextArea body = new TextArea();
        body.setPromptText("Body");

        grid.add(new Label("Notice Title:"), 0, 0);
        grid.add(title, 1, 0);
        grid.add(new Label("Notice Body:"), 0, 1);
        grid.add(body, 1, 1);

        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        title.textProperty().addListener((observable, oldValue, newValue) -> loginButton.setDisable(newValue.trim().isEmpty()));

        dialog.getDialogPane().setContent(grid);
        Platform.runLater(title::requestFocus);

        dialog.setResultConverter(dialogButton -> {

            if (dialogButton == loginButtonType) {

                return new Pair<>(title.getText(), body.getText());
            }

            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();
        result.ifPresent(params -> {

            try {

                NoticeDao.insertNotice(params.getKey(), params.getValue());

            } catch (Exception e) {

                e.printStackTrace();
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        subtitle.requestFocus();
        checkUser();
    }

    private void checkUser() {

        if (Session.user != null) {

            int isAdmin = Session.user.getIsAdmin();

            if (isAdmin == 0) {

                btnUpdate.setDisable(true);
                btnSettings.setDisable(true);
            }
        }
    }

    private void loadStage(String fxml) {

        try {

            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.getIcons().add(new Image("/home/icons/icon.png"));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
