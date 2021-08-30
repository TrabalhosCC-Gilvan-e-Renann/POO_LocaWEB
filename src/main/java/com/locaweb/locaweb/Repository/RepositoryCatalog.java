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
        itens.add(new Series("The Witcher",2,2019,1.08f,"Ação/Aventura",1,8));
    }

    public ArrayList<ItemCatalog> searchByName(String name){
        ArrayList<ItemCatalog> busca = new ArrayList<ItemCatalog>();
        for (ItemCatalog item : itens){
            if(item.getName().toLowerCase().contains(name.toLowerCase())){
                busca.add(item);
            }
        }
        System.out.printf("Busca Encerrada");
        return busca;
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
            try{
                this.itens.remove(index);
                System.out.printf("Bye Bye Item");
                return true;
            }catch (IndexOutOfBoundsException e){
                System.out.printf("Acesso ao item foi perdido, tente novamente os nos contate pelo email Locaweb@locadoraweb.com");
                return false;
            }
        }
    }

    public Boolean update(ItemCatalog item){
        int index = searchById(item.getId());
        if(index == -1) {
            System.out.printf("Item não Existe");
            return false;
        }else{
            try {
                this.itens.get(index).update(item);
                System.out.printf("O Item mudou");
                return true;
            }catch (IndexOutOfBoundsException e){
                System.out.printf("Acesso ao item foi perdido, tente novamente os nos contate pelo email Locaweb@locadoraweb.com");
                return false;
            }
        }
    }

}
