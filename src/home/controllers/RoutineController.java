package home.controllers;

import home.constants.Session;
import home.database.RoutineDao;
import home.model.Notice;
import home.model.Routine;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RoutineController implements Initializable {

    @FXML
    private Button deleteRoutine;
    @FXML
    private TableView<Routine> tbData;
    @FXML
    private TableColumn<Routine, String> routineTitle;
    @FXML
    private Pane routinePane;
    @FXML
    private ImageView routineImage;

    private ObservableList<Routine> routineList;
    private Routine routine;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (Session.user != null) {

            if (Session.user.getIsAdmin() == 0) {

                deleteRoutine.setVisible(false);
            }
        }

        loadRoutines();
    }

    private void loadRoutines() {

        routineList = FXCollections.observableArrayList();
        routineTitle.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getRoutineTitle()));
        tbData.setItems(routineList);

        try {

            routineList.clear();
            routineList.addAll(RoutineDao.getRoutineList());

        } catch (Exception e) {

            e.printStackTrace();
        }

        tbData.setRowFactory(param -> {

            TableRow<Routine> row = new TableRow<>();

            row.setOnMouseClicked(event -> {

                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY) {

                    updateImage(row.getItem());

                } else {

                    this.routine = null;
                    deleteRoutine.setDisable(true);
                }
            });

            return row;
        });
    }

    private void updateImage(Routine routine) {

        deleteRoutine.setDisable(false);
        this.routine = routine;

        Image image = new Image(routine.getRoutineUrl());
        routineImage.setImage(image);
    }

    public void onDeleteClicked(ActionEvent event) {

        if (this.routine != null) {

            routineImage.setImage(null);

            try {

                RoutineDao.deleteRoutine(String.valueOf(this.routine.getRoutineId()));
                routineList.clear();
                routineList.addAll(RoutineDao.getRoutineList());

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }
}
