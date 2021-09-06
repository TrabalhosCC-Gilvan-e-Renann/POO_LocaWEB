package com.locaweb.locaweb.Business;

import com.locaweb.locaweb.Classes.ItemCatalog;
import com.locaweb.locaweb.Classes.Movie;
import com.locaweb.locaweb.Classes.Series;
import com.locaweb.locaweb.Repository.RepositoryCatalog;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class CatalogBusiness {

    private RepositoryCatalog repositorio;

    public CatalogBusiness(RepositoryCatalog repositorio) {
        this.repositorio = repositorio;
    }

    public ArrayList<ItemCatalog> listarCatalogo(){
        return repositorio.getCatalog();
    }
    public ArrayList<ItemCatalog> listarFilmes(){
        try {
            return repositorio.getCatalog().stream().filter(item -> item instanceof Movie)
                    .collect(Collectors.toCollection(ArrayList::new));
        }catch(NullPointerException e){
            showAlert("Item não instanciado", Alert.AlertType.ERROR);
            return null;
        }
    }
    public ArrayList<ItemCatalog> listarSeries(){
        try {
            return repositorio.getCatalog().stream().filter(item -> item instanceof Series)
                    .collect(Collectors.toCollection(ArrayList::new));
        }catch (NullPointerException e){
            showAlert("Item não instanciado", Alert.AlertType.ERROR);
            return null;
        }
    }

    public void adicionar(ItemCatalog item){
        try {
            repositorio.include(item);
            showAlert("Item adicionado com sucesso!",Alert.AlertType.CONFIRMATION);
        }catch (NullPointerException e){
            showAlert("Item não instanciado", Alert.AlertType.ERROR);
        }catch (NumberFormatException e){
            showAlert("Alguns dos campos que era para ter números, recebeu texto", Alert.AlertType.ERROR);
        }
    }

    public ArrayList<ItemCatalog> search(String nome){
        try {
            return repositorio.searchByName(nome);
        }catch (NullPointerException e){
            showAlert("Item não instanciado", Alert.AlertType.ERROR);
            return null;
        }
    }

    public void removerItem(ItemCatalog catalogoSelecionado) {
        try {
            boolean deletou = repositorio.delete(catalogoSelecionado.getId());
            if (deletou) {
                showAlert("Removido com sucesso", Alert.AlertType.CONFIRMATION);
            }
        }catch (NullPointerException e){
            showAlert("Item não instanciado", Alert.AlertType.ERROR);
        }
    }

    public void editar(ItemCatalog newItem) {
        try {
            repositorio.update(newItem);
        }catch (NullPointerException e){
            showAlert("Item não instanciado", Alert.AlertType.ERROR);
        }catch (NumberFormatException e){
            showAlert("Alguns dos campos que era para ter números, recebeu texto", Alert.AlertType.ERROR);
        }

    }

    protected void showAlert(String message, Alert.AlertType alerta){
        Alert alert = new Alert(alerta);
        alert.setHeaderText(message);
        alert.setTitle("Situação do Item do Catálogo");
        alert.show();
    }
}
