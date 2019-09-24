package home.controllers;

import home.database.NoticeDao;
import home.model.Notice;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private Pane noticePane;
    @FXML
    private TableView<Notice> tbData;
    @FXML
    public TableColumn<Notice, String> noticeDate;
    @FXML
    public TableColumn<Notice, String> noticeHeader;
    @FXML
    private Label noticeTitle;
    @FXML
    private TextArea noticeBody;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loadNotices();
    }

    private void loadNotices() {

        ObservableList<Notice> noticeList = FXCollections.observableArrayList();
        noticeDate.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getNoticeDate()));
        noticeHeader.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getNoticeTitle()));
        tbData.setItems(noticeList);
        noticePane.setVisible(false);

        try {

            noticeList.addAll(NoticeDao.getNoticeList());

        } catch (Exception e) {

            e.printStackTrace();
        }

        tbData.setRowFactory(param -> {

            TableRow<Notice> row = new TableRow<>();

            row.setOnMouseClicked(event -> {

                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY) {

                    noticePane.setVisible(true);
                    setNotice(row.getItem());

                } else {

                    noticePane.setVisible(false);
                }
            });

            return row;
        });
    }

    private void setNotice(Notice item) {

        noticeTitle.setText(item.getNoticeTitle());
        noticeBody.setText(item.getNoticeBody());
    }
}
