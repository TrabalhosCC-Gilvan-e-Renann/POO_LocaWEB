package com.locaweb.locaweb.Repository;

import com.locaweb.locaweb.Classes.Account;

import java.util.ArrayList;

public class RepositoryAccounts implements IRepositorioAccounts{

    public ArrayList<Account> getContas() {
        return contas;
    }

    ArrayList<Account> contas;

    public RepositoryAccounts(){
        contas = new ArrayList<Account>();
        contas.add(new Account("Administrador","000","admin","admin","1",true,1));
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
        String cpf = conta.getCPF();
        String cardNumber = conta.getCard();
        String email = conta.getEmail();
        String pass = conta.getPass();
        contas.add(new Account(name,cpf,email,pass,cardNumber,false,contas.size()+1));
    }

    @Override
    public void remover(Account conta) {
        Account minhaConta = consultar(conta.getId());
        if (minhaConta!= null)  contas.remove(minhaConta);
    }

    @Override
    public void atualizar(Account newConta,int userId) {
        Account conta = consultar(userId);
        if (conta!= null) editarDadosConta(newConta,conta);
    }

    private void editarDadosConta(Account nova,Account atual) {
        String name = nova.getName();
        String cpf = nova.getCPF();
        String cardNumber = nova.getCard();
        String email = nova.getEmail();

        atual.setCard(cardNumber);
        atual.setCPF(cpf);
        atual.setEmail(email);
        atual.setName(name);
    }

    @Override
    public Account logar(String email, String senha) {
        for (Account conta : contas) {
            if (conta.getEmail().equals(email) && conta.getPass().equals(senha)) {
                return conta;
            }
        }
        return null;
    }

    @Override
    public Account consultar(Account conta) {
        return null;
    }

    @Override
    public Account consultar(int numero) {

        for (Account conta : contas) if (conta.getId() == numero) return conta;

        return null;
    }


}
