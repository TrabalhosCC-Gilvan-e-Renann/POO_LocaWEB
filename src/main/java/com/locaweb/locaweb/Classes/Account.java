package com.locaweb.locaweb.Classes;

import com.locaweb.locaweb.Business.AccountBusiness;

public class Account extends Client {
    int id;
    boolean blockedForPay;
    boolean blockedForADM;
    String dateOfCreate;
    byte lastPaidMonthly;
    /*Para simplificar vamos considerar que a mensalidade de todas as contas devem ser pagas no dia 1*/
    public Account(String name, String cpf, String email, String pass, String numberCard){
        super(name, cpf, email,pass, numberCard);
        this.blockedForADM = false;
        this.blockedForPay = false;
        this.lastPaidMonthly = 0; //Considere que o Computador pegou o mes atual
        this.dateOfCreate = "00/00/0000"; // considere que o Computador pegou a data atual
        this.id = Integer.parseInt(cpf) + 1; // Considere que o banco de dados, passou o id
    }
    public int PaidMonthly(){
        byte mes=0;
        if(!this.blockedForADM && (this.lastPaidMonthly-mes!=0 ||  this.blockedForPay)){
            System.out.print("Pago");
            /*Imagine aqui a função de pagamento*/
            this.lastPaidMonthly = mes;
            this.blockedForPay = false;
            return 1;
        }else if(this.lastPaidMonthly-mes==0){
            System.out.println("A mensalidade já fooi paga");
            return 0;
        }
        return -1;
    }
    public int Blocked(){
        this.blockedForADM = true;
        return 1;
    }
    public boolean accountIsBlocked(){
        byte mes=0;
        if(mes-this.lastPaidMonthly!=0 || this.blockedForADM){
            this.blockedForPay = true;
            return true;
        }
        return false;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCPF() {
        return cpf;
    }
    public void setCPF(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCard() {
        return numberCard;
    }
    public void setCard(String card) {
        this.numberCard = card;
    }

    @Override
    public String toString() {
        return getName();
    }
}
