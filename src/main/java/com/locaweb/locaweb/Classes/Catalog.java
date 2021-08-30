/*package com.locaweb.locaweb.Classes;

import java.util.ArrayList;

public class Catalog {
	public ArrayList<ItemCatalog> getCatalog() {
        return itens;
    }
    private ArrayList<ItemCatalog> itens;
	
	Catalog(){
            itens = new ArrayList<ItemCatalog>();
	}
	public Integer searchByName(String name){
		try{
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
		}catch (IndexOutOfBoundsException e){
			System.out.printf("Acesso ao item foi perdido, tente novamente os nos contate pelo email Locaweb@locadoraweb.com");
			return -1;
		}
	}
	private Integer searchById(int id){
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
	public Boolean update(int id, String name, int year, String genre){
            int index = searchById(id);
            if(index == -1) {
            	System.out.printf("Item não Existe");
            	return false;
            }else{
				try {
					this.itens.get(index).update(name, year, genre);
					System.out.printf("O Item mudou");
					return true;
				}catch (IndexOutOfBoundsException e){
					System.out.printf("Acesso ao item foi perdido, tente novamente os nos contate pelo email Locaweb@locadoraweb.com");
					return false;
				}
            }
	}
}*/
