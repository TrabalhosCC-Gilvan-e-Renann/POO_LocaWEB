package com.locaweb.locaweb.Classes;

import java.util.ArrayList;

public class Series extends ItemCatalog{
    float timeWatched;
    int seasonWatched;
    int episodeWatched;
    int season;
    int episodes;
    
    public Series(String name, int id, int year, float duration, String genre, int season, int episodes){
        super(name, id,duration, year, genre);
        this.duration = duration;
        this.timeWatched = 0;
        this.episodeWatched = 0;
        this.seasonWatched = 0;
        this.episodes = episodes;
        this.season = season;
    }
    public void update(String name, int year, float duration, String genre, int season, int episodes){
        super.update(name, year, genre);
        this.duration = duration;
        this.episodes = episodes;
        this.season = season;
        if(duration<this.timeWatched){
            this.timeWatched = duration;
        }
        if(season<this.seasonWatched){
            this.seasonWatched = season;
        }
        
    }

    @Override
    public void pause(/*int time, int episode, int season*/){
        /*if(this.timeWatched < time && this.episodeWatched==episode && this.seasonWatched==season){
            this.timeWatched = time;
            return 1;
        }else if(this.episodeWatched<episode || this.seasonWatched<season){
            this.episodeWatched=episode;
            this.seasonWatched=season;
            this.timeWatched = time;
            return 1;
        }
        else{
            return -1;
        }*/
        super.pause();
    }

    @Override
    public void watch(){
        super.watch();
    }
}
