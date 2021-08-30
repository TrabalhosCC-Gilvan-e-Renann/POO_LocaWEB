package com.locaweb.locaweb.view;

import com.locaweb.locaweb.Classes.Account;
import com.locaweb.locaweb.Classes.LocaWEB;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
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
    private TextField EmailInput;

    @FXML
    private PasswordField PassInput;

    @FXML
    private TextField CpfInput;

    @FXML
    private TextField NameInput;

    @FXML
    private TextField CardInput;

    @FXML
    private AnchorPane contaBloqueada;

    @FXML
    private AnchorPane pagarConta;

    @FXML
    private AnchorPane meuPerfil;

    @FXML
    public void CloseClick(ActionEvent event) {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void Pay(ActionEvent event) {
        locaWeb.getContaLogada().PaidMonthly();
        contaBloqueada.setOpacity(0);
        meuPerfil.setOpacity(0);
        pagarConta.setOpacity(0);
        updateButtons();
    }

    @FXML
    public void DenyPay(ActionEvent event) {
        contaBloqueada.setOpacity(1);
        meuPerfil.setOpacity(0);
        pagarConta.setOpacity(0);
    }


    @FXML
    public void ProfileClick(ActionEvent event) {
        contaBloqueada.setOpacity(0);
        meuPerfil.setOpacity(1);
        pagarConta.setOpacity(0);
        meuPerfil.setDisable(false);
        contaBloqueada.setDisable(true);
        pagarConta.setDisable(true);

        EmailInput.setText(locaWeb.getContaLogada().getEmail());
        PassInput.setText(locaWeb.getContaLogada().getPass());
        CpfInput.setText(locaWeb.getContaLogada().getCPF());
        NameInput.setText(locaWeb.getContaLogada().getName());
        CardInput.setText(locaWeb.getContaLogada().getCard());
    }

    @FXML
    public void PayClick(ActionEvent event) {
        contaBloqueada.setOpacity(0);
        meuPerfil.setDisable(true);
        contaBloqueada.setDisable(true);
        pagarConta.setDisable(false);
        meuPerfil.setOpacity(0);
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
                meuPerfil.setOpacity(0);
                pagarConta.setOpacity(0);
            }
        });
    }

    private void updateButtons(){
        catalogBtn.setDisable(locaWeb.getContaLogada().accountIsBlocked());
        payBtn.setDisable(!locaWeb.getContaLogada().accountIsBlocked());
    }


    @FXML
    public void ConfirmClick(ActionEvent event) throws IOException {
        String email = EmailInput.getText();
        String cpf = CpfInput.getText();
        String nome = NameInput.getText();
        String pass = PassInput.getText();
        String card = CardInput.getText();

        Account newConta = new Account(nome,cpf,email,pass,card,false,locaWeb.getContaLogada().getId());
        locaWeb.editarCliente(newConta,locaWeb.getContaLogada().getId());
        olaLabel.setText("Olá, "+locaWeb.getContaLogada().getName());
        meuPerfil.setDisable(true);
        meuPerfil.setOpacity(0);
    }

    @FXML
    public void RemoveClick(ActionEvent event) throws IOException {
        locaWeb.removerConta(locaWeb.getContaLogada());
        BackToLoginView(event);
    }

    @FXML
    public void LogOutClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/login-view.fxml"));
        Parent root = loader.load();

        LoginController controller = loader.getController();
        controller.setLocaWeb(locaWeb);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void BackToLoginView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/login-view.fxml"));
        Parent root = loader.load();

        LoginController controller = loader.getController();
        controller.setLocaWeb(locaWeb);

        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
