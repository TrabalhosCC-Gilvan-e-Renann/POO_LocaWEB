package com.locaweb.locaweb.Classes;

import com.locaweb.locaweb.Business.AccountBusiness;
import com.locaweb.locaweb.Business.CatalogBusiness;
import com.locaweb.locaweb.Exceptions.ClienteJaExisteException;
import com.locaweb.locaweb.Exceptions.ClienteNaoExisteException;
import com.locaweb.locaweb.Repository.RepositoryAccounts;
import com.locaweb.locaweb.Repository.RepositoryCatalog;

import java.util.ArrayList;
import java.util.Date;

public class LocaWEB {

    private AccountBusiness contas;
    private CatalogBusiness catalogo;

    public Account getContaLogada() {
        return contaLogada;
    }

    public void setContaLogada(Account contaLogada) {
        this.contaLogada = contaLogada;
    }

    private Account contaLogada;

    public LocaWEB(){
        this.contaLogada = null;
        this.contas = new AccountBusiness(new RepositoryAccounts());
        this.catalogo = new CatalogBusiness(new RepositoryCatalog());
    }

    //CONTAS
    public Account logar(String email, String senha) throws ClienteNaoExisteException {
        contaLogada = contas.logar(email,senha);
        return contaLogada;
    }

    public void adicionarCliente(String name, String cpf, String email, String pass, String numberCard,boolean blocked) throws ClienteJaExisteException {
        Account conta = new Account(name, cpf, email,pass,numberCard,false,0,blocked,new Date());
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

    public CatalogBusiness getCatalogBusiness(){
        return catalogo;
    }

    public ArrayList<ItemCatalog> getFilmes() {
        return catalogo.listarFilmes();
    }

    public ArrayList<ItemCatalog> getSeries() {
        return catalogo.listarSeries();
    }

    public void removerItem(ItemCatalog catalogoSelecionado) {
        catalogo.removerItem(catalogoSelecionado);
    }

    public void editarCatalago(ItemCatalog newItem) {
        catalogo.editar(newItem);
    }

    public void adicionarCatalogo(ItemCatalog newItem) {
        catalogo.adicionar(newItem);
    }
}


