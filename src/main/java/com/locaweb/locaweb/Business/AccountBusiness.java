package com.locaweb.locaweb.Business;

import com.locaweb.locaweb.Classes.Account;
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

    public Account logar(String email,String senha) {
        return repositorio.logar(email,senha);
    }

    public void adicionar(Account conta) {
        try {
            boolean existe = repositorio.existe(conta.getEmail());
            if (existe) {
                System.out.println("CONTA JÁ EXISTENTE");
            } else {
                repositorio.adicionar(conta);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Cadastrado com Sucesso!");
                alert.setTitle("Situação do Cadastro");
                alert.show();
            }
        }catch (NullPointerException e){
            System.out.println("CONTA NÃO INSTANCIADA");
        }
    }

    public ArrayList<Account> listarContas(){
        try {
            return repositorio.getContas();
        }catch (NullPointerException e){
            System.out.println("CONTA NÃO INSTANCIADA");
            return null;
        }
    }

    public void editarConta(Account conta,int userId) {
        try {
            repositorio.atualizar(conta, userId);
        } catch (NullPointerException e){
            System.out.println("CONTA NÃO INSTANCIADA");
        }
    }

    public void removerConta(Account conta) {
        try {
            repositorio.remover(conta);
        } catch (NullPointerException e){
            System.out.println("CONTA NÃO INSTANCIADA");
        }
    }
}
