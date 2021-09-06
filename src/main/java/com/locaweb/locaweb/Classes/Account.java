package com.locaweb.locaweb.Classes;

import com.locaweb.locaweb.Business.AccountBusiness;

import java.util.Date;
import java.util.Objects;

public class Account extends Client {
    private int id;
    private boolean blockedForPay;

    public boolean isBlockedForPay() {
        return blockedForPay;
    }

    public boolean isBlockedForADM() {
        return blockedForADM;
    }

    public void setBlockedForADM(boolean blockedForADM) {
        this.blockedForADM = blockedForADM;
    }

    private boolean blockedForADM;
    private Date dateOfCreate;
    private Date lastPaidMonthly;
    private boolean isAdmin;

    public ItemCatalog getWatching() {
        return watching;
    }

    public void setWatching(ItemCatalog watching) {
        this.watching = watching;
    }

    private ItemCatalog watching;

    /*Para simplificar vamos considerar que a mensalidade de todas as contas devem ser pagas no dia 1*/
    public Account(String name, String cpf, String email, String pass, String numberCard,boolean isAdmin,int id,boolean blocked){
        super(name, cpf, email,pass, numberCard);
        this.isAdmin = isAdmin;
        this.blockedForADM = blocked;
        this.blockedForPay = true;
        this.lastPaidMonthly = null;
        this.dateOfCreate = new Date(); // considere que o Computador pegou a data atual
        this.id = id; // Considere que o banco de dados, passou o id
    }
    public int PaidMonthly(){
        Date mesAtual=new Date();
        if(!this.blockedForADM && (lastPaidMonthly==null || (lastPaidMonthly.after(mesAtual)) ||  this.blockedForPay)){
            System.out.print("Pago");
            /*Imagine aqui a função de pagamento*/
            this.lastPaidMonthly = mesAtual;
            this.blockedForPay = false;
            return 1;
        }else if(this.lastPaidMonthly.before(mesAtual)){
            System.out.println("A mensalidade já foi paga");
            return 0;
        }
        return -1;
    }

    public boolean accountIsBlocked(){
        Date mesAtual=new Date();
        if(this.blockedForADM){
            return true;
        }else if(this.lastPaidMonthly == null || this.lastPaidMonthly.after(mesAtual) || this.blockedForPay){
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

    public Boolean getIsAdmin() {return isAdmin;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return id == account.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return getName();
    }
}
