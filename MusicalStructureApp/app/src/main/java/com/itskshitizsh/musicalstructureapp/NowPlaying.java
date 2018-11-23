package com.itskshitizsh.musicalstructureapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;


public class NowPlaying extends AppCompatActivity {

    private String currentSongName;
    private String currentSongDetails;
    private String currentTotalTime;

    TextView songName;
    TextView songDetails;
    TextView songTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.now_playing);

        currentSongName = "Maine Tujhko Dekha";
        currentSongDetails = "Neeraj Shridhar, Sukriti Kakar\nGolmaal Again";
        currentTotalTime = "3:27";

        songName = findViewById(R.id.textView);
        songDetails = findViewById(R.id.textView2);
        songTime = findViewById(R.id.totalTime);

        setCurrentSong();

    }

    private void setCurrentSong() {
        songName.setText(currentSongName);
        songDetails.setText(currentSongDetails);
        songTime.setText(currentTotalTime);
    }
}
