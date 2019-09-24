package home.database;

import home.constants.Constants;
import home.model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDao {

    public static Student searchStudent(int rollNo) throws SQLException, ClassNotFoundException {

        String selectStmt = "SELECT * FROM students WHERE roll = " + rollNo;

        try {

            ResultSet resultSet = DatabaseHandler.dbExecuteQuery(selectStmt);
            Student student = getStudentByRoll(resultSet);

            if (student != null) {

                return student;

            } else {

                return new Student();
            }

        } catch (SQLException e) {

            System.out.println("While searching an employee with " + rollNo + " id, an error occurred: " + e);
            throw e;
        }
    }

    private static Student getStudentByRoll(ResultSet resultSet) throws SQLException {

        Student student = null;

        if (resultSet.next()) {

            student = new Student();

            student.setStudentId(resultSet.getInt(Constants.STUDENT_ID));
            student.setStudentName(resultSet.getString(Constants.STUDENT_NAME));
            student.setStudentRoll(resultSet.getInt(Constants.STUDENT_ROLL));
            student.setStudentDepartment(resultSet.getString(Constants.STUDENT_DEPT));
            student.setStudentSession(resultSet.getString(Constants.STUDENT_SESSION));
        }

        return student;
    }

    public static ObservableList<Student> getStudentList() throws SQLException, ClassNotFoundException {

        String selectStmt = "SELECT * FROM students";

        try {

            ResultSet resultSet = DatabaseHandler.dbExecuteQuery(selectStmt);
            return getStudentList(resultSet);

        } catch (SQLException e) {

            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }

    private static ObservableList<Student> getStudentList(ResultSet resultSet) throws SQLException {

        ObservableList<Student> studentList = FXCollections.observableArrayList();

        while (resultSet.next()) {

            Student student = new Student();

            student.setStudentId(resultSet.getInt(Constants.STUDENT_ID));
            student.setStudentName(resultSet.getString(Constants.STUDENT_NAME));
            student.setStudentRoll(resultSet.getInt(Constants.STUDENT_ROLL));
            student.setStudentDepartment(resultSet.getString(Constants.STUDENT_DEPT));
            student.setStudentSession(resultSet.getString(Constants.STUDENT_SESSION));
            student.setStudentEmail(resultSet.getString(Constants.STUDENT_EMAIL));

            studentList.add(student);
        }

        return studentList;
    }

    public static void updateStudent (Student student) throws SQLException, ClassNotFoundException {

        String update = "UPDATE students SET " + Constants.STUDENT_NAME + " = '" + student.getStudentName() + "', " +
                        Constants.STUDENT_DEPT + " = '" + student.getStudentDepartment() + "', " +
                        Constants.STUDENT_ROLL + " = '" + student.getStudentRoll() + "' WHERE " +
                        Constants.STUDENT_ID + " = " + student.getStudentId();

        try {

            DatabaseHandler.dbExecuteUpdate(update);

        } catch (SQLException e) {

            System.out.print("Error occurred while UPDATE Operation: " + e);
            throw e;
        }
    }

    public static void deleteStudent (String id) throws SQLException, ClassNotFoundException {

        String delete = "DELETE FROM students WHERE " + Constants.STUDENT_ID + " = " + id;

        try {

            DatabaseHandler.dbExecuteUpdate(delete);

        } catch (SQLException e) {

            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }

    public static void insertStudent (Student student) throws SQLException, ClassNotFoundException {

        String insert = "INSERT INTO students(" + Constants.STUDENT_NAME + ", " + Constants.STUDENT_ROLL + ", "
                + Constants.STUDENT_DEPT + ", " + Constants.STUDENT_SESSION + ", " + Constants.STUDENT_EMAIL + ") VALUES ('"
                + student.getStudentName() + "', '" + student.getStudentRoll() + "', '" + student.getStudentDepartment() + "', '"
                + student.getStudentSession() + "', '" + student.getStudentEmail() + "')";

        try {

            DatabaseHandler.dbExecuteUpdate(insert);

        } catch (SQLException e) {

            System.out.print("Error occurred while INSERT Operation: " + e);
            throw e;
        }
    }
}
