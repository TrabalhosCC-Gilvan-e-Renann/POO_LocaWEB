package com.locaweb.locaweb.view;

import com.locaweb.locaweb.Classes.Account;
import com.locaweb.locaweb.Classes.LocaWEB;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ClienteDashController implements Initializable {

    private LocaWEB locaWeb;
    private boolean isEdit = false;

    @FXML
    private ListView<Account> usersList;

    @FXML
    private Label userNmame;

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
    private AnchorPane FormPane;

    @FXML
    private Button EditBtn;

    @FXML
    private Button RemoveBtn;


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

        usersList.getSelectionModel().selectedItemProperty().addListener((observableValue, account, t1) -> {
            usuarioSelecionado = usersList.getSelectionModel().getSelectedItem();
            userNmame.setText(usuarioSelecionado.getName());
            EditBtn.setOpacity(1);
            EditBtn.setDisable(false);
            RemoveBtn.setOpacity(1);
            RemoveBtn.setDisable(false);
            FormPane.setOpacity(0);
        });
    }

    @FXML
    protected void EditarClick(ActionEvent event) throws IOException {
        isEdit = true;
        FormPane.setOpacity(1);
        EmailInput.setText(usuarioSelecionado.getEmail());
        PassInput.setText(usuarioSelecionado.getPass());
        CpfInput.setText(usuarioSelecionado.getCPF());
        NameInput.setText(usuarioSelecionado.getName());
        CardInput.setText(usuarioSelecionado.getCard());
    }

    @FXML
    protected void CadastrarClick(ActionEvent event) throws IOException {
        isEdit = false;
        FormPane.setOpacity(1);
        PassInput.setDisable(false);
    }

    @FXML
    protected void RemoverClick(ActionEvent event) throws IOException {

    }

    @FXML
    protected void ConfirmClick(ActionEvent event) throws IOException {
        if(isEdit){
            System.out.println("É EDIT");
        }else {
            String email = EmailInput.getText();
            String senha = PassInput.getText();
            String cpf = CpfInput.getText();
            String nome = NameInput.getText();
            String card = CardInput.getText();

            locaWeb.adicionarCliente(nome,cpf,email,senha,card);
            carregarUsuários();
            ClearInputTextt();
            FormPane.setOpacity(0);

        }
    }

    private void ClearInputTextt(){
        EmailInput.setText("");
        PassInput.setText("");
        CpfInput.setText("");
        NameInput.setText("");
        CardInput.setText("");
    }
}