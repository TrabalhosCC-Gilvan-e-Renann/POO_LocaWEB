package com.locaweb.locaweb.Classes;

public class ItemCatalog {
    String name;
    int id;
    int year;
    String genre;
	
    ItemCatalog(String name, int id, int year, String genre){
	this.name = name;
	this.id = id;
        this.year = year;
        this.genre = genre;
    }
    public void watch(){
        System.out.print("Você esta assistindo: "+this.name);
    }
    public void update(String name, int year, String genre){
        this.name = name;
        this.year = year;
        this.genre = genre;
    }
   
}
