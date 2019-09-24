package home.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Notice {

    private SimpleIntegerProperty noticeId;
    private SimpleStringProperty noticeTitle;
    private SimpleStringProperty noticeBody;
    private SimpleStringProperty noticeDate;

    public Notice() {

        noticeId = new SimpleIntegerProperty();
        noticeTitle = new SimpleStringProperty();
        noticeBody = new SimpleStringProperty();
        noticeDate = new SimpleStringProperty();
    }

    public int getNoticeId() {
        return noticeId.get();
    }

    public SimpleIntegerProperty noticeIdProperty() {
        return noticeId;
    }

    public void setNoticeId(int noticeId) {
        this.noticeId.set(noticeId);
    }

    public String getNoticeTitle() {
        return noticeTitle.get();
    }

    public SimpleStringProperty noticeTitleProperty() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle.set(noticeTitle);
    }

    public String getNoticeBody() {
        return noticeBody.get();
    }

    public SimpleStringProperty noticeBodyProperty() {
        return noticeBody;
    }

    public void setNoticeBody(String noticeBody) {
        this.noticeBody.set(noticeBody);
    }

    public String getNoticeDate() {
        return noticeDate.get();
    }

    public SimpleStringProperty noticeDateProperty() {
        return noticeDate;
    }

    public void setNoticeDate(String noticeDate) {
        this.noticeDate.set(noticeDate);
    }
}
