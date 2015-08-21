package com.example.giuliano.rockchords;

/**
 * Created by Giuliano on 02/06/2015.
 */
public class Music {

    private String chords;
    private String band;
    private String musicName;

    public Music(String chords, String band, String musicName){
        this.chords = chords;
        this.band = band;
        this.musicName = musicName;
    }

    public String getChords(){
        return this.chords;
    }

    public String getBand(){
        return this.band;
    }

    public String getMusicName(){
        return this.musicName;
    }

}
