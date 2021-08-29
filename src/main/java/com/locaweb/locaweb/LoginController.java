package com.locaweb.locaweb;

import com.locaweb.locaweb.Business.AccountBusiness;
import com.locaweb.locaweb.Classes.Account;
import com.locaweb.locaweb.Classes.LocaWEB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;

public class LoginController {

    private LocaWEB locaWeb;

    private Parent root;
    private Stage stage;

    @FXML
    private TextField EmailInput;

    @FXML
    private TextField PassInput;

    @FXML
    private TextField CpfInput;

    @FXML
    private TextField NameInput;

    @FXML
    private TextField CardInput;

    @FXML
    private Label CpfLabel;

    @FXML
    private Label NameLabel;

    @FXML
    private Label CardLabel;

    @FXML
    private Button WantRegister;

    @FXML
    private Button SignInBtn;

    private Boolean isLogin = true;

    @FXML
    protected void LogarClick(ActionEvent event) throws IOException {
        if(isLogin){
            String email = EmailInput.getText();
            String senha = PassInput.getText();

            boolean logado = locaWeb.logar(email,senha);

            if (logado){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("cliente-dash-view.fxml"));
                root = loader.load();

                ClienteDashView controller = loader.getController();
                controller.setLocaWeb(locaWeb);

                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }else{
                System.out.println("erro ao logar");
            }
        }else{
            String email = EmailInput.getText();
            String senha = PassInput.getText();
            String cpf = CpfInput.getText();
            String nome = NameInput.getText();
            String card = CardInput.getText();

            locaWeb.adicionarCliente(nome,cpf,email,senha,card);
        }

    }
    @FXML
    protected void WantRegisterClick(ActionEvent event) throws IOException {
        if(isLogin){
            CpfInput.setDisable(false);
            CpfLabel.setOpacity(1);
            CpfInput.setOpacity(1);

            NameInput.setDisable(false);
            NameLabel.setOpacity(1);
            NameInput.setOpacity(1);

            CardInput.setDisable(false);
            CardLabel.setOpacity(1);
            CardInput.setOpacity(1);

            WantRegister.setText("Quero fazer o Login");
            SignInBtn.setText("Se Cadastrar");
            isLogin = false;
        }else{
            CpfInput.setDisable(true);
            CpfLabel.setOpacity(0);
            CpfInput.setOpacity(0);

            NameInput.setDisable(true);
            NameLabel.setOpacity(0);
            NameInput.setOpacity(0);

            CardInput.setDisable(true);
            CardLabel.setOpacity(0);
            CardInput.setOpacity(0);

            WantRegister.setText("Quero me Cadastrar");
            SignInBtn.setText("Logar");
            isLogin = true;
        }

    }

    public void setLocaWeb(LocaWEB locaWeb) {
        this.locaWeb = locaWeb;
    }
}