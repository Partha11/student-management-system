package home.database;

import home.constants.Constants;
import home.model.Notice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NoticeDao {

    public static ObservableList<Notice> getNoticeList() throws SQLException, ClassNotFoundException {

        String selectStmt = "SELECT * FROM notices";

        try {

            ResultSet resultSet = DatabaseHandler.dbExecuteQuery(selectStmt);
            return getNoticeList(resultSet);

        } catch (SQLException e) {

            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }

    private static ObservableList<Notice> getNoticeList(ResultSet resultSet) throws SQLException {

        ObservableList<Notice> noticeList = FXCollections.observableArrayList();

        while (resultSet.next()) {

            Notice notice = new Notice();

            notice.setNoticeId(resultSet.getInt(Constants.NOTICE_ID));
            notice.setNoticeTitle(resultSet.getString(Constants.NOTICE_TITLE));
            notice.setNoticeBody(resultSet.getString(Constants.NOTICE_BODY));
            notice.setNoticeDate(resultSet.getString(Constants.NOTICE_DATE));

            noticeList.add(notice);
        }

        return noticeList;
    }

    public static void insertNotice(String title, String body) throws SQLException, ClassNotFoundException {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String time = dtf.format(now);

        String insert = "INSERT INTO notices(" + Constants.NOTICE_TITLE + ", " + Constants.NOTICE_BODY + ", "
                + Constants.NOTICE_DATE + ") VALUES ('" + title + "', '" + body + "', '"
                + time + "')";

        try {

            DatabaseHandler.dbExecuteUpdate(insert);

        } catch (SQLException e) {

            System.out.print("Error occurred while INSERT Operation: " + e);
            throw e;
        }
    }
}
