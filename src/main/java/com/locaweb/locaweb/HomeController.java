package com.locaweb.locaweb;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.Console;

public class HomeController {

    @FXML
    private TextField EmailInput;

    @FXML
    private TextField PassInput;

    @FXML
    protected void LogarClick() {
        String email = EmailInput.getText();
        String senha = PassInput.getText();


        if(email.equals("marcos.renann") && senha.equals("123")){
            System.out.println("LOGADO");
        }else{
            System.out.println(email);
            System.out.println(senha);
            System.out.println("NÃO É USUÁRIO");
        }
    }
}