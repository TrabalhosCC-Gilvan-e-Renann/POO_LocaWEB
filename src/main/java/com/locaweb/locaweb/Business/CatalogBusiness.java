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
        try {
            return repositorio.getCatalog().stream().filter(item -> item instanceof Movie)
                    .collect(Collectors.toCollection(ArrayList::new));
        }catch(NullPointerException e){
            System.out.println("CATALOGO NÃO INSTANCIADO");
            return null;
        }
    }
    public ArrayList<ItemCatalog> listarSeries(){
        try {
            return repositorio.getCatalog().stream().filter(item -> item instanceof Series)
                    .collect(Collectors.toCollection(ArrayList::new));
        }catch (NullPointerException e){
            System.out.println("CATALOGO NÃO INSTANCIADO");
            return null;
        }
    }

    public void adicionar(ItemCatalog item){
        try {
            repositorio.include(item);
        }catch (NullPointerException e){
            System.out.println("CATALOGO NÃO INSTANCIADO");
        }
    }

    public ArrayList<ItemCatalog> search(String nome){
        try {
            return repositorio.searchByName(nome);
        }catch (NullPointerException e){
            System.out.println("CATALOGO NÃO INSTANCIADO");
            return null;
        }
    }

    public void removerItem(ItemCatalog catalogoSelecionado) {
        try {
            boolean deletou = repositorio.delete(catalogoSelecionado.getId());
            if (deletou) {
                System.out.println("DEU BOM!");
            }
        }catch (NullPointerException e){
            System.out.println("CATALOGO NÃO INSTANCIADO");
        }
    }

    public void editar(ItemCatalog newItem) {
        try {
            repositorio.update(newItem);
        }catch (NullPointerException e){
            System.out.println("CATALOGO NÃO INSTANCIADO");
        }
    }
}
