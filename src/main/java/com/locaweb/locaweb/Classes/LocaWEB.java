package com.locaweb.locaweb.Classes;

import com.locaweb.locaweb.Business.AccountBusiness;
import com.locaweb.locaweb.Repository.RepositoryAccounts;

import java.util.ArrayList;

public class LocaWEB {

    private AccountBusiness contas;
    //filmes

    public LocaWEB(){
        this.contas = new AccountBusiness(new RepositoryAccounts());
    }

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
}
