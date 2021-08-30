package com.locaweb.locaweb.view;

import com.locaweb.locaweb.Classes.Account;
import com.locaweb.locaweb.Classes.ItemCatalog;
import com.locaweb.locaweb.Classes.LocaWEB;
import com.locaweb.locaweb.Classes.Series;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ClientDashController implements Initializable {

    private  LocaWEB locaWeb;

    @FXML
    private Label olaLabel;

    @FXML
    private Label cardLabel;

    @FXML
    private Label mySeasons;

    @FXML
    private Label blockLabel;

    @FXML
    private Button closeBtn;
    @FXML
    private Button ProfileBtn;
    @FXML
    private Button catalogBtn;
    @FXML
    private Button payBtn;
    @FXML
    private Button watchPauseBtn;

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
    private TextField SearchInput;

    @FXML
    private AnchorPane contaBloqueada;

    @FXML
    private AnchorPane pagarConta;

    @FXML
    private AnchorPane watchPane;

    @FXML
    private AnchorPane meuPerfil;

    @FXML
    private Label myTitulo;

    @FXML
    private Label myYear;

    @FXML
    private Label myDuration;

    @FXML
    private Label myGenre;

    @FXML
    private ListView<ItemCatalog> catalogList;

    private ObservableList<ItemCatalog> obsCatalogo;
    private ItemCatalog catalogoSelecionado;

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
    public void SeeCatalog(ActionEvent event) {
        watchPane.setOpacity(1);
        watchPane.setDisable(false);
        contaBloqueada.setOpacity(0);
        meuPerfil.setOpacity(0);
        pagarConta.setOpacity(0);
        meuPerfil.setDisable(true);
        contaBloqueada.setDisable(true);
        pagarConta.setDisable(true);
    }

    @FXML
    public void ProfileClick(ActionEvent event) {
        watchPane.setOpacity(0);
        watchPane.setDisable(true);
        contaBloqueada.setOpacity(0);
        meuPerfil.setOpacity(1);
        pagarConta.setOpacity(0);
        meuPerfil.setDisable(false);
        contaBloqueada.setDisable(true);
        pagarConta.setDisable(true);

        NameInput.setText(locaWeb.getContaLogada().getName());
        EmailInput.setText(locaWeb.getContaLogada().getEmail());
        PassInput.setText(locaWeb.getContaLogada().getPass());
        CpfInput.setText(locaWeb.getContaLogada().getCPF());
        CardInput.setText(locaWeb.getContaLogada().getCard());
    }

    @FXML
    public void PayClick(ActionEvent event) {
        watchPane.setOpacity(0);
        watchPane.setDisable(true);
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
                if(locaWeb.getContaLogada().isBlockedForADM()){
                    blockLabel.setText("É necessário entrar em contato com o Administrador para desbloquear a sua conta!");
                } else if(locaWeb.getContaLogada().isBlockedForPay()) {
                    blockLabel.setText("É necessário pagar a mensalidade para desbloquear a sua conta!");
                }
                contaBloqueada.setOpacity(1);
                meuPerfil.setOpacity(0);
                pagarConta.setOpacity(0);
            }
            carregarCatalogo(this.locaWeb.getCatalogo());

            catalogList.getSelectionModel().selectedItemProperty().addListener((observableValue, catalogo, t1) -> {
                catalogoSelecionado = catalogList.getSelectionModel().getSelectedItem();
                if(catalogoSelecionado!=null) {
                    myTitulo.setText(catalogoSelecionado.getName());
                    myDuration.setText(""+catalogoSelecionado.getDuration());
                    myYear.setText(""+catalogoSelecionado.getYear());
                    myGenre.setText(catalogoSelecionado.getGenre());
                    watchPauseBtn.setDisable(false);
                    watchPauseBtn.setOpacity(1);
                    if(verifyWatchSituation()) watchPauseBtn.setText("Pausar");
                        else watchPauseBtn.setText("Assistir");
                    if(catalogoSelecionado instanceof Series) mySeasons.setText(((Series) catalogoSelecionado).getSeasons() +" Temporada(s) com " +((Series) catalogoSelecionado).getEpisodes() + " episódio(s) no total");
                        else mySeasons.setText("");
                }
            });
        });

        SearchInput.textProperty().addListener((obs, oldText, newText) -> {
            // do what you need with newText here, e.g.
            if(newText.length() > 2)  {
                carregarCatalogo(locaWeb.getCatalogBusiness().search(newText));
            }else{
                carregarCatalogo(this.locaWeb.getCatalogo());
            }

        });

    }

    private void updateButtons(){
        catalogBtn.setDisable(locaWeb.getContaLogada().accountIsBlocked());
        if(locaWeb.getContaLogada().isBlockedForADM()) payBtn.setDisable(true);
        else payBtn.setDisable(!locaWeb.getContaLogada().isBlockedForPay());

        ProfileBtn.setDisable(locaWeb.getContaLogada().isBlockedForADM());
    }


    @FXML
    public void ConfirmClick(ActionEvent event) throws IOException {
        String email = EmailInput.getText();
        String cpf = CpfInput.getText();
        String nome = NameInput.getText();
        String pass = PassInput.getText();
        String card = CardInput.getText();
        boolean blocked = locaWeb.getContaLogada().isBlockedForADM();

        Account newConta = new Account(nome,cpf,email,pass,card,false,locaWeb.getContaLogada().getId(),blocked);
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
        BackToLoginView(event);
    }

    @FXML
    public void WatchPause() {
       if(verifyWatchSituation()){
           watchPauseBtn.setText("Assistir");
           catalogoSelecionado.pause();
       }else{
           watchPauseBtn.setText("Pausar");
           for(ItemCatalog item:obsCatalogo){
               item.pause();
           }
           catalogoSelecionado.watch();
       }
    }

    public boolean verifyWatchSituation(){
        return catalogoSelecionado.getIsWatching();
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

    public void carregarCatalogo(ArrayList<ItemCatalog> getCatalago){
        obsCatalogo = FXCollections.observableArrayList(getCatalago);
        catalogList.setItems(obsCatalogo);
    }


}
