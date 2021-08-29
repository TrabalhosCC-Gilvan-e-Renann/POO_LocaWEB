package com.locaweb.locaweb;

import com.locaweb.locaweb.Classes.Account;
import com.locaweb.locaweb.Classes.LocaWEB;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ClienteDashView implements Initializable {

    private LocaWEB locaWeb;

    @FXML
    private ListView<Account> usersList;

    @FXML
    private Label label;

    private ObservableList<Account> obsUsers;
    private Account usuarioSelecionado;

    public void setLocaWeb(LocaWEB locaWeb) {
        this.locaWeb = locaWeb;
        carregarUsuários();
    }

    public void carregarUsuários(){
        ArrayList<Account> contas = this.locaWeb.getContas();
        System.out.println(contas);
        obsUsers = FXCollections.observableArrayList(contas);
        usersList.setItems(obsUsers);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        usersList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Account>() {
            @Override
            public void changed(ObservableValue<? extends Account> observableValue, Account account, Account t1) {
                usuarioSelecionado = usersList.getSelectionModel().getSelectedItem();
                label.setText(usuarioSelecionado.getEmail());
            }
        });
    }
}
