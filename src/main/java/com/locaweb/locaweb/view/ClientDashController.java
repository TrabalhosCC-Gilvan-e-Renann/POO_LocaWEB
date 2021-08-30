package com.locaweb.locaweb.view;

import com.locaweb.locaweb.Classes.LocaWEB;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientDashController implements Initializable {

    private  LocaWEB locaWeb;

    @FXML
    private Label olaLabel;

    @FXML
    private Label cardLabel;

    @FXML
    private Button closeBtn;
    @FXML
    private Button catalogBtn;
    @FXML
    private Button payBtn;

    @FXML
    private AnchorPane contaBloqueada;

    @FXML
    private AnchorPane pagarConta;

    @FXML
    public void CloseClick(ActionEvent event) {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void Pay(ActionEvent event) {
        locaWeb.getContaLogada().PaidMonthly();
        contaBloqueada.setOpacity(0);
        pagarConta.setOpacity(0);
        updateButtons();
    }

    @FXML
    public void DenyPay(ActionEvent event) {
        contaBloqueada.setOpacity(1);
        pagarConta.setOpacity(0);
    }
    @FXML
    public void PayClick(ActionEvent event) {
        contaBloqueada.setOpacity(0);
        pagarConta.setOpacity(1);
        cardLabel.setText("Pagar a Mensalidade usando o cartão "+locaWeb.getContaLogada().getCard()+" ?");
    }

    public void setLocaWeb(LocaWEB locaWeb) {
        this.locaWeb = locaWeb;

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Platform.runLater(() -> {
            olaLabel.setText("Olá, "+locaWeb.getContaLogada().getName());
            updateButtons();
            if(locaWeb.getContaLogada().accountIsBlocked()){
                contaBloqueada.setOpacity(1);
                pagarConta.setOpacity(0);
            }
        });
    }

    private void updateButtons(){
        catalogBtn.setDisable(locaWeb.getContaLogada().accountIsBlocked());
        payBtn.setDisable(!locaWeb.getContaLogada().accountIsBlocked());
    }


}
