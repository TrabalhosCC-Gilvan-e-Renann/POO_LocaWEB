package com.locaweb.locaweb.Classes;

import com.locaweb.locaweb.Business.AccountBusiness;
import com.locaweb.locaweb.Repository.RepositoryAccounts;

public class LocaWEB {

    private AccountBusiness contas;
    //filmes

    public LocaWEB(){
        this.contas = new AccountBusiness(new RepositoryAccounts());
    }

    public boolean logar(String email, String senha) {
        return contas.logar(email, senha);
    }

    public AccountBusiness getContas() {
        return contas;
    }

}
