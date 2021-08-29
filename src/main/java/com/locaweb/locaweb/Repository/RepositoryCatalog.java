package com.locaweb.locaweb.Repository;

import com.locaweb.locaweb.Classes.Account;
import com.locaweb.locaweb.Classes.ItemCatalog;
import com.locaweb.locaweb.Classes.Movie;

import java.util.ArrayList;

public class RepositoryCatalog implements IRepositoryCatalog{

    public ArrayList<ItemCatalog> getCatalogo() {
        return catalogo;
    }

    ArrayList<ItemCatalog> catalogo;

    public RepositoryCatalog(){
        catalogo = new ArrayList<ItemCatalog>();
        catalogo.add(new Movie("Aladdin",1,2019,2.08f,"Música/Infantil"));
    }


    @Override
    public void adicionar(ItemCatalog item) {

    }

    @Override
    public void remover(ItemCatalog item) {

    }

    @Override
    public void atualizar(ItemCatalog item) {

    }

    @Override
    public Account consultar(ItemCatalog item) {
        return null;
    }

    @Override
    public Account consultar(int numero) {
        return null;
    }
}
