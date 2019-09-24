package home.database;

import home.constants.Constants;
import home.constants.Session;
import home.model.Student;
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
            user.setName(resultSet.getString(Constants.USER_NAME));
        }

        return user;
    }

    public static void updateName (String name, int id) throws SQLException, ClassNotFoundException {

        String update = "UPDATE users SET " + Constants.USER_NAME + " = '" + name + "' WHERE " +
                Constants.USER_ID + " = " + id;

        try {

            DatabaseHandler.dbExecuteUpdate(update);
            Session.user.setName(name);

        } catch (SQLException e) {

            System.out.print("Error occurred while UPDATE Operation: " + e);
            throw e;
        }
    }

    public static void updateEmail (String email, int id) throws SQLException, ClassNotFoundException {

        String update = "UPDATE users SET " + Constants.USER_EMAIL + " = '" + email + "' WHERE " +
                Constants.USER_ID + " = " + id;

        try {

            DatabaseHandler.dbExecuteUpdate(update);
            Session.user.setEmail(email);

        } catch (SQLException e) {

            System.out.print("Error occurred while UPDATE Operation: " + e);
            throw e;
        }
    }

    public static void updatePassword (String password, int id) throws SQLException, ClassNotFoundException {

        String update = "UPDATE users SET " + Constants.USER_PASSWORD + " = '" + password + "' WHERE " +
                Constants.USER_ID + " = " + id;

        try {

            DatabaseHandler.dbExecuteUpdate(update);

        } catch (SQLException e) {

            System.out.print("Error occurred while UPDATE Operation: " + e);
            throw e;
        }
    }

    public static void insertUser (User user, String password) throws SQLException, ClassNotFoundException {

        String insert = "INSERT INTO users(" + Constants.USER_NAME + ", " + Constants.USER_EMAIL + ", "
                + Constants.USER_PASSWORD + ", " + Constants.IS_ADMIN + ") VALUES ('"
                + user.getName() + "', '" + user.getEmail() + "', '" + password + "', '"
                + user.getIsAdmin() + "')";

        try {

            DatabaseHandler.dbExecuteUpdate(insert);

        } catch (SQLException e) {

            System.out.print("Error occurred while INSERT Operation: " + e);
            throw e;
        }
    }
}
