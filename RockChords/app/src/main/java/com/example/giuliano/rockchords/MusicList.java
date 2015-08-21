package com.example.giuliano.rockchords;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Giuliano on 08/06/2015.
 */
public class MusicList {

    List<Music> musics = new LinkedList<Music>();
    Connection connection = new Connection();

    public void getMusics(){
        try {
            musics = connection.sendGet();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> returnAllBands(){
        List<String> bands = new LinkedList<String>();
        for(Music music:musics){
            bands.add(music.getBand());
        }
        return bands;
    }

    public List<String> returnMusicsByBand(String band){
        List<String> found = new LinkedList<String>();
        for(Music music: musics){
            if(music.getBand().equals(band)) found.add(music.getMusicName());
        }
        return found;
    }

    public String returnChords(String band, String musicName){
        for(Music music: musics){
            if(music.getBand().equals(band) && music.getMusicName().equals(musicName)) return music.getChords();
        }
        return "Sorry...";
    }



}
