package com.locaweb.locaweb.Exceptions;

public class ClienteNaoExisteException extends Exception{
    public ClienteNaoExisteException(){
        super("Email e/ou Senha estão incorretos");
    }
}