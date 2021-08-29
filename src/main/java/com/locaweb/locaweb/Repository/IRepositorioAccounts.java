package com.locaweb.locaweb.Repository;

import com.locaweb.locaweb.Classes.Account;

public interface IRepositorioAccounts {
    public abstract void adicionar(Account conta);
    void remover(Account conta);
    void atualizar(int id);
    boolean logar(String email,String senha);
    boolean existe(String email);
    Account consultar(Account conta);
    Account consultar(String numero);
}

