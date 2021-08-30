package com.locaweb.locaweb.Classes;

import java.util.Objects;

public class Movie extends ItemCatalog{
    private boolean continuation;

    public String getNameContinuation() {
        return nameContinuation;
    }

    private String nameContinuation;
    private float timeWatch;
    
    public Movie(String name, int id, int year, float duration, String genre){
        super(name, id,duration, year, genre);
        this.duration = duration;
        this.continuation = false;
        this.timeWatch = 0;
    }
    public Movie(String name, int id, int year, float duration, String genre, String nameContinuation){
        super(name, id,duration, year, genre);
        this.duration = duration;
        this.continuation = true;
        this.timeWatch = 0;
        this.nameContinuation = nameContinuation;    
    }

    @Override
    public void update(ItemCatalog movie){
        if(movie instanceof Movie){
            super.update(movie);
            if((((Movie) movie).getNameContinuation().length()>0)){
                this.continuation = true;
                this.nameContinuation = ((Movie) movie).getNameContinuation();
            }else{
                this.continuation = false;
            }
        }

    }

    @Override
    public void pause(){
        /*if(this.timeWatch < time){
            this.timeWatch = time;
            return 1;
        }else{
            return -1;
        }*/
        super.pause();
    }

    @Override
    public void watch(){
        System.out.print("Você esta assistindo o filme: "+getName());
    }
    
}
