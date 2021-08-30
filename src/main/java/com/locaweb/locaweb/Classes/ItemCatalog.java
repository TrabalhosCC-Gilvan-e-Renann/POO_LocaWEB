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
    protected int id;
    protected int year;
    protected String genre;

    public boolean getIsWatching() {
        return isWatching;
    }


    public void setWatching(boolean watching) {
        isWatching = watching;
    }

    boolean isWatching;

    public float getDuration() {
        return duration;
    }

    float duration;
	
    ItemCatalog(String name, int id, float duration,int year, String genre){
	this.name = name;
	this.id = id;
    this.year = year;
    this.genre = genre;
    this.duration = duration;
    }
    public void watch(){
        System.out.print("Você esta assistindo: "+this.name);
        setWatching(true);
    }

    public void pause(){
        System.out.print("Você pausou : "+this.name);
        setWatching(false);
    }

    public void update(String name, int year, String genre){
        this.name = name;
        this.year = year;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return getName();
    }
}
