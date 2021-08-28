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
    public boolean existe(String email){
        for (Account conta : contas) {
            if (conta.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void adicionar(Account conta) {
        String name = conta.getName();
        int cpf = conta.getCPF();
        int cardNumber = conta.getCard();
        String email = conta.getEmail();
        String pass = conta.getPass();

        contas.add(new Account(name,cpf,email,pass,cardNumber));
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
