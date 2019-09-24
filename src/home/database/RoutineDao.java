package home.database;

import home.constants.Constants;
import home.model.Notice;
import home.model.Routine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoutineDao {

    public static ObservableList<Routine> getRoutineList() throws SQLException, ClassNotFoundException {

        String selectStmt = "SELECT * FROM routines";

        try {

            ResultSet resultSet = DatabaseHandler.dbExecuteQuery(selectStmt);
            return getRoutineList(resultSet);

        } catch (SQLException e) {

            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }

    private static ObservableList<Routine> getRoutineList(ResultSet resultSet) throws SQLException {

        ObservableList<Routine> noticeList = FXCollections.observableArrayList();

        while (resultSet.next()) {

            Routine notice = new Routine();

            notice.setRoutineId(resultSet.getInt(Constants.ROUTINE_ID));
            notice.setRoutineTitle(resultSet.getString(Constants.ROUTINE_TITLE));
            notice.setRoutineUrl(resultSet.getString(Constants.ROUTINE_URL));

            noticeList.add(notice);
        }

        return noticeList;
    }

    public static void deleteRoutine (String id) throws SQLException, ClassNotFoundException {

        String delete = "DELETE FROM routines WHERE " + Constants.ROUTINE_ID + " = " + id;

        try {

            DatabaseHandler.dbExecuteUpdate(delete);

        } catch (SQLException e) {

            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }
}
