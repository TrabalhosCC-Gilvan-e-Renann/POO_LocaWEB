package com.locaweb.locaweb.Classes;

import com.locaweb.locaweb.Business.AccountBusiness;
import com.locaweb.locaweb.Business.CatalogBusiness;
import com.locaweb.locaweb.Repository.RepositoryAccounts;
import com.locaweb.locaweb.Repository.RepositoryCatalog;

import java.util.ArrayList;

public class LocaWEB {

    private AccountBusiness contas;
    private CatalogBusiness catalogo;

    public LocaWEB(){
        this.contas = new AccountBusiness(new RepositoryAccounts());
        this.catalogo = new CatalogBusiness(new RepositoryCatalog());
    }

    //CONTAS
    public boolean logar(String email, String senha) {
        return contas.logar(email, senha);
    }

    public void adicionarCliente(String name, String cpf, String email, String pass, String numberCard) {
        Account conta = new Account(name, cpf, email,pass,numberCard);
        contas.adicionar(conta);
    }

    public ArrayList<Account> getContas() {
        return contas.listarContas();
    }

    public void editarCliente(Account conta,int userId) {
        contas.editarConta(conta,userId);
    }

    public void removerConta(Account conta) {
        contas.removerConta(conta);
    }


    //CATALOGO

    public ArrayList<ItemCatalog> getCatalogo() {
        return catalogo.listarCatalogo();
    }

    public ArrayList<ItemCatalog> getFilmes() {
        return catalogo.listarFilmes();
    }

    public ArrayList<ItemCatalog> getSeries() {
        return catalogo.listarSeries();
    }

}


