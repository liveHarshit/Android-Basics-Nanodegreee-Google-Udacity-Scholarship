package com.itskshitizsh.musicalstructureapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.itskshitizsh.musicalstructureapp.songClass.Song;

import java.util.ArrayList;

public class LibraryActivity extends AppCompatActivity {

    private ArrayList<Song> queue;
    private SongListAdaptor adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        String label;

        listView = findViewById(R.id.listView);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Play Song Queue
                startActivity(new Intent(LibraryActivity.this, NowPlaying.class));
            }
        });

        if (getIntent().hasExtra("label")) {
            label = getIntent().getStringExtra("label");

            this.setTitle(label);
            queue = new ArrayList<>();
            designActivity(label);
        }
    }

    private void designActivity(String label) {
        switch (label) {
            case "Favorites":

                queue.add(new Song("Maine Tujhko Dekha", "Neeraj Shridhar, Sukriti Kakar", "Golmaal Again", true, "3:27"));
                queue.add(new Song("Laagi Na Choote", "Arijit Singh,, Shreya Ghoshal", "Gentleman", true, "3:29"));
                queue.add(new Song("Disco Disco", "", "Gentleman", true, "2:46"));
                queue.add(new Song("Gal Ban Gayi", "Meet Bros, Yo Yo Honey Singh", "Gal Ban Gayi", true, "4:14"));
                queue.add(new Song("Tu Mera Bhai Nahi Hai", "Gandharv Sachdev, Raftaar", "Fukrey Returns", true, "2:37"));
                queue.add(new Song("Mehbooba", "Neha Kakkar, Yasser Desai, Raftaar, Mohammed Rafi", "Fukrey Returns", true, "2:59"));
                queue.add(new Song("Rang Laal", "Dev Negi, John Abraham, Aditi Singh Sharma", "Force 2", true, "3:45"));
                queue.add(new Song("Khair Mangda", "Atif Aslam", "A Flying Jatt", true, "4:17"));
                queue.add(new Song("Do Chaar Din", "Rahul Vadiya", "Do Chaar Din", true, "5:11"));
                builtFavorites(queue);
                break;
            case "Playlists":
                queue.add(new Song("Maine Tujhko Dekha", "Neeraj Shridhar, Sukriti Kakar", "Golmaal Again", true, "3:27"));
                queue.add(new Song("Laagi Na Choote", "Arijit Singh,, Shreya Ghoshal", "Gentleman", true, "3:29"));
                queue.add(new Song("O Chandralekha", "Vishal Dadlani, Jonita Gandhi", "Gentleman", false, "3:12"));
                queue.add(new Song("Disco Disco", "", "Gentleman", true, "2:46"));
                queue.add(new Song("Bandook Meri Laila", "Ash King, Jigar Saraiya, Sidharth Malhotra, Raftar", "Gentleman", false, "3:34"));
                queue.add(new Song("Gal Ban Gayi", "Meet Bros, Yo Yo Honey Singh", "Gal Ban Gayi", true, "4:14"));
                queue.add(new Song("Tu Mera Bhai Nahi Hai", "Gandharv Sachdev, Raftaar", "Fukrey Returns", true, "2:37"));
                queue.add(new Song("Mehbooba", "Neha Kakkar, Yasser Desai, Raftaar, Mohammed Rafi", "Fukrey Returns", true, "2:59"));
                queue.add(new Song("Rang Laal", "Dev Negi, John Abraham, Aditi Singh Sharma", "Force 2", true, "3:45"));
                queue.add(new Song("Khair Mangda", "Atif Aslam", "A Flying Jatt", true, "4:17"));
                queue.add(new Song("Do Chaar Din", "Rahul Vadiya", "Do Chaar Din", true, "5:11"));
                builtPlaylists(queue);
                break;
            default:
                break;
        }
    }

    private void builtPlaylists(ArrayList<Song> queue) {
        adapter = new SongListAdaptor(getApplicationContext(), queue);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(LibraryActivity.this, NowPlaying.class));
            }
        });
    }

    private void builtFavorites(ArrayList<Song> queue) {
        adapter = new SongListAdaptor(getApplicationContext(), queue);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(LibraryActivity.this, NowPlaying.class));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId()==android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
