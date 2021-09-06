package com.locaweb.locaweb.view;

import com.locaweb.locaweb.Classes.*;
import com.locaweb.locaweb.Exceptions.ClienteJaExisteException;
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
    private boolean isEditItem = false;

    @FXML
    private Button closeBtn;

    @FXML
    private ToggleGroup BlockedUser;
    @FXML
    private ToggleGroup Item;

    @FXML
    private RadioButton blockUserBtn;
    @FXML
    private RadioButton unblockUserBtn;

    @FXML
    private RadioButton SerieBtn;
    @FXML
    private RadioButton filmeBtn;

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
    private TextField TitleInput;

    @FXML
    private TextField YearInput;

    @FXML
    private TextField TimeInput;

    @FXML
    private TextField Continuation;
    @FXML
    private TextField Season;
    @FXML
    private TextField Episode;

    @FXML
    private TextField GenreInput;

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
    protected void ConfirmItem(ActionEvent event) throws IOException {
        String title = TitleInput.getText();
        String genre = GenreInput.getText();
        String year = YearInput.getText();
        String time = TimeInput.getText();

        try {
            if (isEditItem) {
                int catalogoId = catalogoSelecionado.getId();
                if (Item.getSelectedToggle() == filmeBtn) {
                    String continuation = Continuation.getText();
                    Movie newItem;
                    if (continuation.length() > 0) {
                        newItem = new Movie(title, catalogoId, Integer.parseInt(year), Float.parseFloat(time), genre, continuation);
                    } else
                        newItem = new Movie(title, catalogoId, Integer.parseInt(year), Float.parseFloat(time), genre);
                    locaWeb.editarCatalago(newItem);
                } else if (Item.getSelectedToggle() == SerieBtn) {
                    String season = Season.getText();
                    String episode = Episode.getText();
                    Series newItem;
                    newItem = new Series(title, catalogoId, Integer.parseInt(year), Float.parseFloat(time), genre, Integer.parseInt(season), Integer.parseInt(episode));
                    locaWeb.editarCatalago(newItem);
                }
            } else {
                if (Item.getSelectedToggle() == filmeBtn) {
                    String continuation = Continuation.getText();
                    Movie newItem;
                    if (continuation.length() > 0) {
                        newItem = new Movie(title, 0, Integer.parseInt(year), Float.parseFloat(time), genre, continuation);
                    } else newItem = new Movie(title, 0, Integer.parseInt(year), Float.parseFloat(time), genre);
                    locaWeb.adicionarCatalogo(newItem);
                } else if (Item.getSelectedToggle() == SerieBtn) {
                    String season = Season.getText();
                    String episode = Episode.getText();
                    Series newItem;
                    newItem = new Series(title, 0, Integer.parseInt(year), Float.parseFloat(time), genre, Integer.parseInt(season), Integer.parseInt(episode));
                    locaWeb.adicionarCatalogo(newItem);
                }
            }
            carregarCatalogo(this.locaWeb.getCatalogo());
            ClearInputItemTextt();
            FormPaneItem.setOpacity(0);
        }catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Alguns dos campos que era para ter números, recebeu texto(duração: float, ano,temporadas,episódios: int)");
            alert.setTitle("Situação do Item do Catálogo");
            alert.show();
        }
    }

    private void ClearInputItemTextt() {
        TitleInput.setText("");
        GenreInput.setText("");
        YearInput.setText("");
        TimeInput.setText("");
        Continuation.setText("");
        Season.setText("");
        Episode.setText("");
    }

    @FXML
    protected void ConfirmClick(ActionEvent event) throws IOException, ClienteJaExisteException {
        String email = EmailInput.getText();
        String senha = PassInput.getText();
        String cpf = CpfInput.getText();
        String nome = NameInput.getText();
        String card = CardInput.getText();
        boolean blocked = BlockedUser.getSelectedToggle() == blockUserBtn;

        if(isEdit){
            int userId = usuarioSelecionado.getId();
            Account newConta = new Account(nome,cpf,email,senha,card,false,userId,blocked);
            locaWeb.editarCliente(newConta,userId);
        }else {
            locaWeb.adicionarCliente(nome,cpf,email,senha,card,blocked);
        }
        carregarUsuários();
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
        TitleInput.setText(catalogoSelecionado.getName());
        GenreInput.setText(catalogoSelecionado.getGenre());
        YearInput.setText(""+catalogoSelecionado.getYear());
        TimeInput.setText(""+catalogoSelecionado.getDuration());

        isEditItem = true;
        FormPaneItem.setOpacity(1);
        if(catalogoSelecionado instanceof Movie){
            Continuation.setText(((Movie) catalogoSelecionado).getNameContinuation());
            Continuation.setDisable(false);
            Continuation.setOpacity(1);

            Season.setOpacity(0);
            Season.setDisable(true);
            Episode.setDisable(true);
            Episode.setOpacity(0);

            filmeBtn.setDisable(false);
            filmeBtn.setSelected(true);
            SerieBtn.setDisable(true);
        }else if(catalogoSelecionado instanceof Series){
            Continuation.setDisable(true);
            Continuation.setOpacity(0);

            Season.setOpacity(1);
            Season.setDisable(false);
            Episode.setDisable(false);
            Episode.setOpacity(1);
            Season.setText(((Series) catalogoSelecionado).getSeasons()+"");
            Episode.setText(((Series) catalogoSelecionado).getEpisodes()+"");

            filmeBtn.setDisable(true);
            SerieBtn.setDisable(false);
            SerieBtn.setSelected(true);
        }

    }

    @FXML
    public void CadastrarItem(ActionEvent event) {

        isEditItem = false;
        ClearInputItemTextt();

        FormPaneItem.setOpacity(1);
        Continuation.setDisable(false);
        Continuation.setOpacity(1);

        Season.setOpacity(0);
        Season.setDisable(true);
        Episode.setDisable(true);
        Episode.setOpacity(0);

        filmeBtn.setDisable(false);
        filmeBtn.setSelected(true);
        SerieBtn.setDisable(false);
    }

    @FXML
    public void isMovie(ActionEvent event) {
        Continuation.setDisable(false);
        Continuation.setOpacity(1);

        Season.setOpacity(0);
        Season.setDisable(true);
        Episode.setDisable(true);
        Episode.setOpacity(0);
    }

    @FXML
    public void isSerie(ActionEvent event) {
        Continuation.setDisable(true);
        Continuation.setOpacity(0);

        Season.setOpacity(1);
        Season.setDisable(false);
        Episode.setDisable(false);
        Episode.setOpacity(1);
    }
    @FXML
    public void RemoverItem(ActionEvent event) {

        locaWeb.removerItem(catalogoSelecionado);
        carregarCatalogo(this.locaWeb.getCatalogo());
        ClearInputItemTextt();
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
