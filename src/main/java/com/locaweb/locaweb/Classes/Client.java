package com.locaweb.locaweb.Classes;

import java.util.ArrayList;

public class Client {
    protected String name;
    protected String cpf;
    protected String email;
    protected String pass;
    protected String numberCard;
   
   Client(String name, String cpf, String email,String pass, String numberCard){
       this.name = name;
       this.cpf = cpf;
       this.email = email;
       this.pass = pass;
       this.numberCard = numberCard;
   }
}
