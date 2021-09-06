package com.locaweb.locaweb.Business;

import com.locaweb.locaweb.Classes.Account;
import com.locaweb.locaweb.Exceptions.ClienteJaExisteException;
import com.locaweb.locaweb.Exceptions.ClienteNaoExisteException;
import com.locaweb.locaweb.Repository.IRepositorioAccounts;
import com.locaweb.locaweb.Repository.RepositoryAccounts;
import javafx.scene.control.Alert;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AccountBusiness {

    private RepositoryAccounts repositorio;

    public AccountBusiness(RepositoryAccounts repositorio) {
        this.repositorio = repositorio;
    }

    public Account logar(String email,String senha) throws ClienteNaoExisteException {
        try {
            Account conta = repositorio.logar(email, senha);
            if (conta == null) {
                throw new ClienteNaoExisteException();
            } else {
                return conta;
            }
        }catch (NullPointerException e){
            showAlert("Conta não instanciada", Alert.AlertType.ERROR);
        }catch (ClienteNaoExisteException e){
            showAlert(e.getMessage(), Alert.AlertType.ERROR);
        }
        return null;
    }

    public void adicionar(Account conta) throws ClienteJaExisteException {
        try {
            boolean existe = repositorio.existe(conta.getEmail());
            if (existe) {
                throw new ClienteJaExisteException();
            } else {
                repositorio.adicionar(conta);
                showAlert("Cadastrado com êxito", Alert.AlertType.CONFIRMATION);
            }
        }catch (NullPointerException e){
            showAlert("Conta não instanciada", Alert.AlertType.ERROR);
        }catch (ClienteJaExisteException e){
            showAlert(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public ArrayList<Account> listarContas(){
        try {
            return repositorio.getContas();
        }catch (NullPointerException e){
            showAlert("Contas não instanciadas", Alert.AlertType.ERROR);
            return null;
        }
    }

    public void editarConta(Account conta,int userId) {
        try {
            repositorio.atualizar(conta, userId);
        } catch (NullPointerException e){
            showAlert("Conta não instanciada", Alert.AlertType.ERROR);
        }
    }

    public void removerConta(Account conta) {
        try {
            repositorio.remover(conta);
        } catch (NullPointerException e){
            showAlert("Conta não instanciada", Alert.AlertType.ERROR);
        }
    }

    protected void showAlert(String message, Alert.AlertType alerta){
        Alert alert = new Alert(alerta);
        alert.setHeaderText(message);
        alert.setTitle("Situação da Conta");
        alert.show();
    }
}
