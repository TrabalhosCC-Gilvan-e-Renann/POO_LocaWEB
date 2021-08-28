package com.locaweb.locaweb.Repository;

import com.locaweb.locaweb.Classes.Account;

import java.util.ArrayList;

public class RepositoryAccounts implements IRepositorioAccounts{

    ArrayList<Account> contas;

    public RepositoryAccounts(){
        contas = new ArrayList<Account>();
        contas.add(new Account("Administrador",000,"admin@admin.com","admin",1));
    }

    @Override
    public void adicionar(Account conta) {

    }

    @Override
    public void remover(Account conta) {

    }

    @Override
    public void atualizar(Account conta) {

    }

    @Override
    public boolean logar(String email, String senha) {
        boolean logado = false;

        for (Account conta : contas) {
            if (conta.getEmail().equals(email) && conta.getPass().equals(senha)) {
                logado = true;
                break;
            }
        }

        return logado;
    }

    @Override
    public Account consultar(Account conta) {
        return null;
    }

    @Override
    public Account consultar(String numero) {
        return null;
    }
}
