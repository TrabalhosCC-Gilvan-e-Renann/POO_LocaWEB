package com.locaweb.locaweb;

import com.locaweb.locaweb.Business.AccountBusiness;
import com.locaweb.locaweb.Classes.Account;
import com.locaweb.locaweb.Classes.LocaWEB;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.Console;
import java.util.ArrayList;

public class HomeController {

    private LocaWEB locaWeb = new LocaWEB();

    @FXML
    private TextField EmailInput;

    @FXML
    private TextField PassInput;

    @FXML
    protected void LogarClick() {
        String email = EmailInput.getText();
        String senha = PassInput.getText();

        boolean logado = locaWeb.logar(email,senha);

        if (logado){
            System.out.println(email + " está logado");
        }else{
            System.out.println("erro ao logar");
        }
    }
}