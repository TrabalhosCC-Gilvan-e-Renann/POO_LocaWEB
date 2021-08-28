module com.locaweb.locaweb {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.locaweb.locaweb to javafx.fxml;
    exports com.locaweb.locaweb;
}