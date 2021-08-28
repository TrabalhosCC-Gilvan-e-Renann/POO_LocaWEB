package com.locaweb.locaweb.Business;

import com.locaweb.locaweb.Classes.Account;
import com.locaweb.locaweb.Repository.IRepositorioAccounts;
import com.locaweb.locaweb.Repository.RepositoryAccounts;

public class AccountBusiness {

    private IRepositorioAccounts repositorio;

    public AccountBusiness(IRepositorioAccounts repositorio) {
        this.repositorio = repositorio;
    }

    public boolean logar(String email,String senha) {
        return repositorio.logar(email,senha);
    }

    public void adicionar(Account conta) {

    }

}
