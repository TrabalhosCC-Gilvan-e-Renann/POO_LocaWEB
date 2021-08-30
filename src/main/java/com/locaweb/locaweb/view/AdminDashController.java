package com.locaweb.locaweb.view;

import com.locaweb.locaweb.Classes.Account;
import com.locaweb.locaweb.Classes.ItemCatalog;
import com.locaweb.locaweb.Classes.LocaWEB;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminDashController implements Initializable {

    private LocaWEB locaWeb;
    private boolean isEdit = false;

    @FXML
    private Button closeBtn;

    @FXML
    private ToggleGroup BlockedUser;

    @FXML
    private RadioButton blockUserBtn;
    @FXML
    private RadioButton unblockUserBtn;

    @FXML
    private ListView<Account> usersList;

    @FXML
    private ListView<ItemCatalog> catalogList;

    @FXML
    private Label userNmame;
    @FXML
    private Label titleLabel;

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
    private AnchorPane FormPaneItem;

    @FXML
    private Button EditBtn;

    @FXML
    private Button RemoveBtn;

    @FXML
    private Button EditItemBtn;

    @FXML
    private Button RemoveItemBtn;


    private ObservableList<Account> obsUsers;
    private Account usuarioSelecionado;

    private ObservableList<ItemCatalog> obsCatalogo;
    private ItemCatalog catalogoSelecionado;

    public void setLocaWeb(LocaWEB locaWeb) {
        this.locaWeb = locaWeb;
    }

    public void carregarUsuários(){
        ArrayList<Account> contas = this.locaWeb.getContas();
        obsUsers = FXCollections.observableArrayList(contas);
        usersList.setItems(obsUsers);
    }

    public void carregarCatalogo(ArrayList<ItemCatalog> getCatalago){
        ArrayList<ItemCatalog> catalogo = getCatalago;
        obsCatalogo = FXCollections.observableArrayList(catalogo);
        catalogList.setItems(obsCatalogo);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Platform.runLater(() -> {
            carregarUsuários();
            carregarCatalogo(this.locaWeb.getCatalogo());
        });

        usersList.getSelectionModel().selectedItemProperty().addListener((observableValue, account, t1) -> {
            usuarioSelecionado = usersList.getSelectionModel().getSelectedItem();
            if(usuarioSelecionado!=null) userNmame.setText(usuarioSelecionado.getName()); else userNmame.setText("");
            EditBtn.setOpacity(1);
            EditBtn.setDisable(false);
            RemoveBtn.setOpacity(1);
            RemoveBtn.setDisable(false);
            FormPane.setOpacity(0);
            if(usuarioSelecionado!=null){
                if(usuarioSelecionado.isBlockedForADM())  blockUserBtn.setSelected(true);
                else unblockUserBtn.setSelected(true);
            }

        });


        catalogList.getSelectionModel().selectedItemProperty().addListener((observableValue, account, t1) -> {
            catalogoSelecionado = catalogList.getSelectionModel().getSelectedItem();
            if(catalogoSelecionado!=null) titleLabel.setText(catalogoSelecionado.getName()); else titleLabel.setText("");
            EditItemBtn.setOpacity(1);
            EditItemBtn.setDisable(false);
            RemoveItemBtn.setOpacity(1);
            RemoveItemBtn.setDisable(false);
            FormPaneItem.setOpacity(0);

        });

    }

    @FXML
    protected void EditarClick(ActionEvent event) throws IOException {
        isEdit = true;
        FormPane.setOpacity(1);
        PassInput.setDisable(true);
        EmailInput.setText(usuarioSelecionado.getEmail());
        PassInput.setText(usuarioSelecionado.getPass());
        CpfInput.setText(usuarioSelecionado.getCPF());
        NameInput.setText(usuarioSelecionado.getName());
        CardInput.setText(usuarioSelecionado.getCard());
    }

    @FXML
    protected void CadastrarClick(ActionEvent event) throws IOException {
        isEdit = false;
        ClearInputTextt();
        FormPane.setOpacity(1);
        PassInput.setDisable(false);
    }

    @FXML
    protected void RemoverClick(ActionEvent event) throws IOException {
        locaWeb.removerConta(usuarioSelecionado);
        if(usuarioSelecionado == locaWeb.getContaLogada()){
            BackToLoginView(event);
        }else{
            carregarUsuários();
            ClearInputTextt();
            FormPane.setOpacity(0);

            EditBtn.setOpacity(0);
            EditBtn.setDisable(true);
            RemoveBtn.setOpacity(0);
            RemoveBtn.setDisable(true);
            FormPane.setOpacity(0);
        }
    }

    @FXML
    protected void ConfirmClick(ActionEvent event) throws IOException {
        String email = EmailInput.getText();
        String senha = PassInput.getText();
        String cpf = CpfInput.getText();
        String nome = NameInput.getText();
        String card = CardInput.getText();
        boolean blocked = BlockedUser.getSelectedToggle() == blockUserBtn;
        System.out.println("BLOCKED"+blocked);

        if(isEdit){
            int userId = usuarioSelecionado.getId();
            Account newConta = new Account(nome,cpf,email,senha,card,false,userId,blocked);
            locaWeb.editarCliente(newConta,userId);
        }else {
            locaWeb.adicionarCliente(nome,cpf,email,senha,card,blocked);
        }
        carregarUsuários();
        ClearInputTextt();
        FormPane.setOpacity(0);
    }

    private void ClearInputTextt(){
        EmailInput.setText("");
        PassInput.setText("");
        CpfInput.setText("");
        NameInput.setText("");
        CardInput.setText("");
    }

    @FXML
    public void CloseClick(ActionEvent event) {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void LogOutClick(ActionEvent event) throws IOException {
        BackToLoginView(event);
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

    @FXML
    public void FilterAll(ActionEvent event) {
        carregarCatalogo(this.locaWeb.getCatalogo());
    }

    @FXML
    public void EditarItem(ActionEvent event) {

    }

    @FXML
    public void CadastrarItem(ActionEvent event) {

    }

    @FXML
    public void RemoverItem(ActionEvent event) {

        locaWeb.removerItem(catalogoSelecionado);
        carregarCatalogo(this.locaWeb.getCatalogo());
        //ClearInputTextt();
        FormPaneItem.setOpacity(0);

        EditItemBtn.setOpacity(0);
        EditItemBtn.setDisable(true);
        RemoveItemBtn.setOpacity(0);
        RemoveItemBtn.setDisable(true);
        FormPaneItem.setOpacity(0);

    }


    @FXML
    public void FilterSeries(ActionEvent event) {
        carregarCatalogo(this.locaWeb.getSeries());
    }

    @FXML
    public void FilterMovies(ActionEvent event) {
        carregarCatalogo(this.locaWeb.getFilmes());
    }
}
