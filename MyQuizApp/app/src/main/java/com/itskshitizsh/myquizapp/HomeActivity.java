package com.itskshitizsh.myquizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
    public void startQuiz(View view){
        startActivity(new Intent(HomeActivity.this,PlaygroundActivity.class));
        Toast.makeText(getApplicationContext(),"All the best!\nEnjoy Learning",Toast.LENGTH_SHORT).show();
        finish();
    }
}
