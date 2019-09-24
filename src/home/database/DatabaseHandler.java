package home.database;

import com.sun.rowset.CachedRowSetImpl;

import java.sql.*;

class DatabaseHandler {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static Connection connection = null;

    private static void dbConnect() throws SQLException, ClassNotFoundException {

        try {

            Class.forName(JDBC_DRIVER);

        } catch (ClassNotFoundException e) {

            e.printStackTrace();
            throw e;
        }

        try {

            connection = DriverManager.getConnection("jdbc:mysql://localhost/student_management", "root", "");

        } catch (SQLException e) {

            e.printStackTrace();
            throw e;
        }
    }

    private static void dbDisconnect() {

        try {

            if (connection != null && !connection.isClosed()) {

                connection.close();
            }

        } catch (Exception e){

            e.printStackTrace();
        }
    }

    static ResultSet dbExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException {

        Statement statement = null;
        ResultSet resultSet = null;
        CachedRowSetImpl crs;

        try {

            dbConnect();
            System.out.println("Select statement: " + queryStmt + "\n");

            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryStmt);
            crs = new CachedRowSetImpl();
            crs.populate(resultSet);

        } catch (SQLException e) {

            System.out.println("Problem occurred at executeQuery operation : " + e);
            throw e;

        } finally {
            if (resultSet != null) {

                resultSet.close();
            }

            if (statement != null) {

                statement.close();
            }

            dbDisconnect();
        }

        return crs;
    }

    static void dbExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException {

        Statement statement = null;

        try {

            dbConnect();
            statement = connection.createStatement();
            statement.executeUpdate(sqlStmt);

        } catch (SQLException e) {

            System.out.println("Problem occurred at executeUpdate operation : " + e);
            throw e;

        } finally {

            if (statement != null) {

                statement.close();
            }

            dbDisconnect();
        }
    }
}
