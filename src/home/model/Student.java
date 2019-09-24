package home.model;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Student {

    private SimpleIntegerProperty studentId;
    private SimpleStringProperty studentName;
    private SimpleStringProperty studentSession;
    private SimpleStringProperty studentDepartment;
    private SimpleIntegerProperty studentRoll;
    private SimpleStringProperty studentEmail;

    public Student() {

        studentId = new SimpleIntegerProperty();
        studentName = new SimpleStringProperty();
        studentSession = new SimpleStringProperty();
        studentDepartment = new SimpleStringProperty();
        studentRoll = new SimpleIntegerProperty();
        studentEmail = new SimpleStringProperty();
    }

    public String getStudentEmail() {
        return studentEmail.get();
    }

    public SimpleStringProperty studentEmailProperty() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail.set(studentEmail);
    }

    public int getStudentId() {
        return studentId.get();
    }

    public void setStudentId(int studentId) {
        this.studentId.set(studentId);
    }

    public String getStudentDepartment() {
        return studentDepartment.get();
    }

    public SimpleStringProperty studentDepartmentProperty() {
        return studentDepartment;
    }

    public void setStudentDepartment(String studentDepartment) {
        this.studentDepartment.set(studentDepartment);
    }

    public SimpleIntegerProperty studentIdProperty() {
        return studentId;
    }

    public String getStudentName() {
        return studentName.get();
    }

    public SimpleStringProperty studentNameProperty() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName.set(studentName);
    }

    public String getStudentSession() {
        return studentSession.get();
    }

    public SimpleStringProperty studentSessionProperty() {
        return studentSession;
    }

    public void setStudentSession(String studentSession) {
        this.studentSession.set(studentSession);
    }

    public int getStudentRoll() {
        return studentRoll.get();
    }

    public SimpleIntegerProperty studentRollProperty() {
        return studentRoll;
    }

    public void setStudentRoll(int studentRoll) {
        this.studentRoll.set(studentRoll);
    }
}