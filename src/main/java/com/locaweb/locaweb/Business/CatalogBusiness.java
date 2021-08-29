package com.locaweb.locaweb.Business;

import com.locaweb.locaweb.Classes.ItemCatalog;
import com.locaweb.locaweb.Repository.RepositoryCatalog;
import java.util.ArrayList;

public class CatalogBusiness {

    private RepositoryCatalog repositorio;

    public CatalogBusiness(RepositoryCatalog repositorio) {
        this.repositorio = repositorio;
    }

    public ArrayList<ItemCatalog> listarCatalogo(){
        return repositorio.getCatalogo();
    }
    public ArrayList<ItemCatalog> listarFilmes(){return repositorio.getCatalogo();}
    public ArrayList<ItemCatalog> listarSeries(){
        return repositorio.getCatalogo();
    }

}
