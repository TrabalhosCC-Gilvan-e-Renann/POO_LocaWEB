package com.locaweb.locaweb.Repository;

import com.locaweb.locaweb.Classes.Account;

public interface IRepositorioAccounts {
    public abstract void adicionar(Account conta);
    void remover(Account conta);
    void atualizar(Account conta,int id);
    boolean logar(String email,String senha);
    boolean existe(String email);
    Account consultar(Account conta);
    Account consultar(int numero);
}

