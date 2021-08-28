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
    protected void LogarClick(ActionEvent event) throws IOException {
        String email = EmailInput.getText();
        String senha = PassInput.getText();

        boolean logado = locaWeb.logar(email,senha);

        if (logado){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("cliente-dash-view.fxml"));
            root = loader.load();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else{
            System.out.println("erro ao logar");
        }
    }

    public void setLocaWeb(LocaWEB locaWeb) {
        this.locaWeb = locaWeb;
    }
}