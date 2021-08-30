package com.locaweb.locaweb.Repository;

import com.locaweb.locaweb.Classes.Account;
import com.locaweb.locaweb.Classes.ItemCatalog;
import com.locaweb.locaweb.Classes.Movie;
import com.locaweb.locaweb.Classes.Series;

import java.util.ArrayList;

public class RepositoryCatalog implements IRepositoryCatalog{

    public ArrayList<ItemCatalog> getCatalog() {
        return itens;
    }

    ArrayList<ItemCatalog> itens;

    public RepositoryCatalog(){
        itens = new ArrayList<ItemCatalog>();
        itens.add(new Movie("Aladdin",1,2019,2.08f,"Música/Infantil"));
        itens.add(new Series("The Witcher",2,2019,1.08f,"Música/Infantil",1,8));
    }

    public Integer searchByName(String name){
        int pos=-1;
        int n = this.itens.size();
        for (int i=0; i<n; i++) {
            if(name==this.itens.get(i).getName()){
                pos = i;
                System.out.printf("Item Encintrado");
            }
        }
        System.out.printf("Busca Encerrada");
        return itens.get(pos).getId();
    }
    public Integer searchById(int id){
        int pos=-1;
        int n = this.itens.size();
        for (int i=0; i<n; i++) {
            if(id==this.itens.get(i).getId()){
                pos=i;
                System.out.printf("Item Encintrado");
            }
        }
        System.out.printf("Busca Encerrada");
        return pos;
    }
    public void include(ItemCatalog Item){
        this.itens.add(Item);
    }
    public Boolean delete(int id){
        int index = searchById(id);
        if(index == -1) {
            System.out.printf("Item não Existe");
            return false;
        }else{
            this.itens.remove(index);
            System.out.printf("Bye Bye Item");
            return true;
        }
    }
    public Boolean update(int id, String name, int year, String genre){
        int index = searchById(id);
        if(index == -1) {
            System.out.printf("Item não Existe");
            return false;
        }else{
            this.itens.get(index).update(name, year, genre);
            System.out.printf("O Item mudou");
            return true;
        }
    }
}
