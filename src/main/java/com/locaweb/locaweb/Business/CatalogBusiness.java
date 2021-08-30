package com.locaweb.locaweb.Business;

import com.locaweb.locaweb.Classes.ItemCatalog;
import com.locaweb.locaweb.Classes.Movie;
import com.locaweb.locaweb.Classes.Series;
import com.locaweb.locaweb.Repository.RepositoryCatalog;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class CatalogBusiness {

    private RepositoryCatalog repositorio;

    public CatalogBusiness(RepositoryCatalog repositorio) {
        this.repositorio = repositorio;
    }

    public ArrayList<ItemCatalog> listarCatalogo(){
        return repositorio.getCatalog();
    }
    public ArrayList<ItemCatalog> listarFilmes(){
        return repositorio.getCatalog().stream().filter(item -> item instanceof Movie)
                .collect(Collectors.toCollection(ArrayList::new));
    }
    public ArrayList<ItemCatalog> listarSeries(){
        return repositorio.getCatalog().stream().filter(item -> item instanceof Series)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void adicionar(ItemCatalog item){

    }

    public ArrayList<ItemCatalog> search(String nome){
        return repositorio.searchByName(nome);
    }
}
