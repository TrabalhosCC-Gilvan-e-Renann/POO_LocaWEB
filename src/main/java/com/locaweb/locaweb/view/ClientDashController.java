package com.locaweb.locaweb.view;

import com.locaweb.locaweb.Classes.LocaWEB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ClientDashController {

    private  LocaWEB locaWeb;

    @FXML
    private Button closeBtn;

    @FXML
    public void CloseClick(ActionEvent event) {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }

    public void setLocaWeb(LocaWEB locaWeb) {
        this.locaWeb = locaWeb;
    }

}
