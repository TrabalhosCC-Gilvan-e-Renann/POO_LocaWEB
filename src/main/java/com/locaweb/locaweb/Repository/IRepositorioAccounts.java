package com.locaweb.locaweb.Repository;

import com.locaweb.locaweb.Classes.Account;

public interface IRepositorioAccounts {
    public abstract void adicionar(Account conta);
    void remover(Account conta);
    void atualizar(Account conta);
    boolean logar(String email,String senha);
    Account consultar(Account conta);
    Account consultar(String numero);
}

