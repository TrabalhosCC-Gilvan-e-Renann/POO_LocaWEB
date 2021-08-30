package com.locaweb.locaweb.Classes;

import java.util.ArrayList;

public class Series extends ItemCatalog{
    private float timeWatched;
    private int seasonWatched;
    private int episodeWatched;
    private int seasons;

    public int getSeasons() {
        return seasons;
    }

    public int getEpisodes() {
        return episodes;
    }

    private int episodes;
    
    public Series(String name, int id, int year, float duration, String genre, int seasons, int episodes){
        super(name, id,duration, year, genre);
        this.duration = duration;
        this.episodes = episodes;
        this.seasons = seasons;
    }
    public void update(String name, int year, float duration, String genre, int seasons, int episodes){
        super.update(name, year, genre);
        this.duration = duration;
        this.episodes = episodes;
        this.seasons = seasons;
        if(duration<this.timeWatched){
            this.timeWatched = duration;
        }
        if(seasons<this.seasonWatched){
            this.seasonWatched = seasons;
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
        System.out.print("Você esta assistindo a serie: "+getName());
    }
}
