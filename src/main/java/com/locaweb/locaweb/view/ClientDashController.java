package com.locaweb.locaweb.view;

import com.locaweb.locaweb.Classes.LocaWEB;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientDashController implements Initializable {

    private  LocaWEB locaWeb;

    @FXML
    private Label olaLabel;
    @FXML
    private Button closeBtn;
    @FXML
    private Button catalogBtn;
    @FXML
    private Button payBtn;

    @FXML
    public void CloseClick(ActionEvent event) {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void PayClick(ActionEvent event) {
        locaWeb.getContaLogada().PaidMonthly();
        updateButtons();
    }

    public void setLocaWeb(LocaWEB locaWeb) {
        this.locaWeb = locaWeb;

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Platform.runLater(() -> {
            olaLabel.setText("Olá, "+locaWeb.getContaLogada().getName());
            updateButtons();
        });
    }

    private void updateButtons(){
        catalogBtn.setDisable(locaWeb.getContaLogada().accountIsBlocked());
        payBtn.setDisable(!locaWeb.getContaLogada().accountIsBlocked());
    }


}
