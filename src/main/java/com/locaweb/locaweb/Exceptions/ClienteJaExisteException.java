package com.locaweb.locaweb.Exceptions;

public class ClienteJaExisteException extends Exception{
    public ClienteJaExisteException(){
        super("Uma conta com esse email já existe!");
    }
}

