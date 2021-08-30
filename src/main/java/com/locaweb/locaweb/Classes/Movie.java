package com.locaweb.locaweb.Classes;

public class Movie extends ItemCatalog{
    boolean continuation;
    String nameContinuation;
    float timeWatch;
    
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
    public void update(String name, int year, float duration, String genre, String... nameContinuation){
        super.update(name, year, genre);
        this.duration = duration;
        if(nameContinuation.length>0){
            this.continuation = true;
            this.nameContinuation = nameContinuation[0];
        }else{
            this.continuation = false;
        }
        if(duration<timeWatch){
            this.timeWatch = duration;
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
        super.watch();
    }
    
}
