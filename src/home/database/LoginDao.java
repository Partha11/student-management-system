package home.database;

import home.constants.Constants;
import home.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {

    public static User loginUser(String email, String password, int isAdmin) throws SQLException, ClassNotFoundException {

        String selectStmt = "SELECT * FROM users WHERE " + Constants.USER_EMAIL + " = '" + email + "' AND "
                + Constants.USER_PASSWORD + " = '" + password + "' AND " + Constants.IS_ADMIN + " = " + isAdmin;

        try {

            ResultSet resultSet = DatabaseHandler.dbExecuteQuery(selectStmt);
            return verifyUser(resultSet);

        } catch (SQLException e) {

            System.out.println("While searching an employee with " + email + " id, an error occurred: " + e);
            throw e;
        }
    }

    private static User verifyUser(ResultSet resultSet) throws SQLException {

        User user = null;

        if (resultSet.next()) {

            user = new User();

            user.setId(resultSet.getInt(Constants.USER_ID));
            user.setEmail(resultSet.getString(Constants.USER_EMAIL));
            user.setIsAdmin(resultSet.getInt(Constants.IS_ADMIN));
        }

        return user;
    }
}
