package home.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Routine {

    private SimpleIntegerProperty routineId;
    private SimpleStringProperty routineTitle;
    private SimpleStringProperty routineUrl;

    public Routine() {

        routineId = new SimpleIntegerProperty();
        routineTitle = new SimpleStringProperty();
        routineUrl = new SimpleStringProperty();
    }

    public int getRoutineId() {
        return routineId.get();
    }

    public SimpleIntegerProperty routineIdProperty() {
        return routineId;
    }

    public void setRoutineId(int routineId) {
        this.routineId.set(routineId);
    }

    public String getRoutineTitle() {
        return routineTitle.get();
    }

    public SimpleStringProperty routineTitleProperty() {
        return routineTitle;
    }

    public void setRoutineTitle(String routineTitle) {
        this.routineTitle.set(routineTitle);
    }

    public String getRoutineUrl() {
        return routineUrl.get();
    }

    public SimpleStringProperty routineUrlProperty() {
        return routineUrl;
    }

    public void setRoutineUrl(String routineUrl) {
        this.routineUrl.set(routineUrl);
    }
}
