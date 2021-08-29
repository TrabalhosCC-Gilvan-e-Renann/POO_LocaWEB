module com.locaweb.locaweb {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.locaweb.locaweb to javafx.fxml;
    exports com.locaweb.locaweb;
    exports com.locaweb.locaweb.view;
    opens com.locaweb.locaweb.view to javafx.fxml;
}