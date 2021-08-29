ackage com.locaweb.locaweb.Classes;

import java.util.ArrayList;

public class Catalog {
	public ArrayList<ItemCatalog> getCatalog() {
        return itens;
    }
    ArrayList<ItemCatalog> itens;
	
	Catalog(){
            itens = new ArrayList<ItemCatalog>();
	}
	public Integer searchByName(String name){
            int pos=-1;
	    int n = this.itens.size();
    	    for (i=0; i<n; i++) {
		if(name==this.itens.get(i).getName()){
			pos = i;
			System.out.printf("Item Encintrado");
		}
            }
            System.out.printf("Busca Encerrada");    
            return id;
	}
	public Integer searchById(int id){
            int pos=-1;
	    int n = this.itens.size();
            for (i=0; i<n; i++) {
		if(id==this.itens.get(i).getId()){
			pos=i;
			System.out.printf("Item Encintrado");
		}
            }
            System.out.printf("Busca Encerrada");    
            return pos;
	}
	public void include(Object Item){
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
            return false;
	}
}
