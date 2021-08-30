package com.locaweb.locaweb.Classes;

public class ItemCatalog {
    public int getId() {
        return id;
    }
    public int getYear() {
        return year;
    }
    public String getGenre() {
        return genre;
    }
    public String getName() {
        return name;
    }

    protected String name;
    private int id;
    protected int year;
    protected String genre;
	
    ItemCatalog(String name, int id, int year, String genre){
	    this.name = name;
	    this.id = id;
        this.year = year;
        this.genre = genre;
    }
    public void watch(){}

    void update(String name, int year, String genre){
        this.name = name;
        this.year = year;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return getName();
    }
}
