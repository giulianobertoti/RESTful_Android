package com.example.giuliano.rockchords;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;
import java.util.LinkedList;

public class MainActivity extends Activity {

    MusicList musics = new MusicList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        musics.getMusics();

        List<String> bands = musics.returnAllBands();


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, bands);

        final Spinner spinnerBand = (Spinner) findViewById(R.id.chooseBand);

        final Spinner spinnerMusic = (Spinner) findViewById(R.id.chooseMusic);

        spinnerBand.setAdapter(adapter);



        spinnerBand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                String selectedBand = String.valueOf(spinnerBand.getSelectedItem());

                List<String> musicNames = musics.returnMusicsByBand(selectedBand);

                ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, musicNames);


                spinnerMusic.setAdapter(adapter2);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        spinnerMusic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                String selectedBand = String.valueOf(spinnerBand.getSelectedItem());
                String selectedMusic = String.valueOf(spinnerMusic.getSelectedItem());

                String result = musics.returnChords(selectedBand, selectedMusic);

                TextView resultArea = (TextView) findViewById(R.id.result);

                resultArea.setText(result);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

    }

}
