package com.itskshitizsh.musicalstructureapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;

public class HomeActivity extends AppCompatActivity {

    private Toast toast;

    private String optionsMenu[] = {"Favorites", "Now Playing", "Playlists", "Settings", "About"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        CircleMenu circleMenu;

        circleMenu = findViewById(R.id.circle_menu);

        circleMenu.setMainMenu(Color.parseColor("#CDCDCD"), R.mipmap.icon_menu, R.mipmap.icon_cancel).addSubMenu(Color.parseColor("#258CFF"), R.mipmap.favorite).addSubMenu(Color.parseColor("#30A400"), R.mipmap.play).addSubMenu(Color.parseColor("#FF4B32"), R.mipmap.playlist).addSubMenu(Color.parseColor("#8A39FF"), R.mipmap.settings).addSubMenu(Color.parseColor("#FF6A00"), R.mipmap.info).setOnMenuSelectedListener(new OnMenuSelectedListener() {

            @Override
            public void onMenuSelected(int index) {
                clicked(index);
            }

        }).setOnMenuStatusChangeListener(new OnMenuStatusChangeListener() {

            @Override
            public void onMenuOpened() {
            }

            @Override
            public void onMenuClosed() {
            }

        });
    }

    private void toastMessage(String message) {

        if (toast != null) {
            toast.cancel();
            toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void clicked(int index) {

        toastMessage(optionsMenu[index]);
        switch (index) {
            case 0:
                startActivity(new Intent(HomeActivity.this, LibraryActivity.class).putExtra("label", optionsMenu[index]));
                break;
            case 1:
                startActivity(new Intent(HomeActivity.this, NowPlaying.class));
                break;
            case 2:
                startActivity(new Intent(HomeActivity.this, LibraryActivity.class).putExtra("label", optionsMenu[index]));
                break;
            case 3:
                startActivity(new Intent(HomeActivity.this, SettingsActivity.class));
                break;
            case 4:
                startActivity(new Intent(HomeActivity.this, AboutActivity.class));
                break;
            default:
                break;
        }
    }
}
