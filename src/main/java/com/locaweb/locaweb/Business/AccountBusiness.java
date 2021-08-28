package com.locaweb.locaweb.Business;

import com.locaweb.locaweb.Classes.Account;
import com.locaweb.locaweb.Repository.IRepositorioAccounts;
import com.locaweb.locaweb.Repository.RepositoryAccounts;
import javafx.scene.control.Alert;

public class AccountBusiness {

    private IRepositorioAccounts repositorio;

    public AccountBusiness(IRepositorioAccounts repositorio) {
        this.repositorio = repositorio;
    }

    public boolean logar(String email,String senha) {
        return repositorio.logar(email,senha);
    }

    public void adicionar(Account conta) {
        boolean existe = repositorio.existe(conta.getEmail());
        if(existe){
            System.out.println("CONTA JÁ EXISTENTE");
        } else {
            repositorio.adicionar(conta);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Cadastrado com Sucesso!");
            alert.setTitle("Situação do Cadastro");
            alert.show();
        }
    }

}
