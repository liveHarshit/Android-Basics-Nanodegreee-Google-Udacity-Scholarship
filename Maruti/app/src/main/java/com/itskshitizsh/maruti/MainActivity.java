package com.itskshitizsh.maruti;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    boolean switchFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = findViewById(R.id.img);
        switchFlag = true;
    }

    public void showAbout(View view) {
        if (switchFlag) {
            img.setImageResource(R.drawable.cafedelux);
            switchFlag = false;
        } else {
            img.setImageResource(R.drawable.cafe);
            switchFlag = true;
        }
    }

    public void openWebsite(View view) {
        Toast.makeText(getApplicationContext(), getString(R.string.website_alert), Toast.LENGTH_SHORT).show();
        //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.myBestCafe.com")));
    }

    public void callUs(View view) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(getString(R.string.call_us))));
    }

    public void tip(View view) {
        Toast.makeText(getApplicationContext(), getString(R.string.tip_aboutUs), Toast.LENGTH_SHORT).show();
    }

    public void feedback(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String subject = getString(R.string.mail_subject);
        String message = getString(R.string.message_body);
        intent.putExtra(Intent.EXTRA_EMAIL, getString(R.string.mail_id));
        intent.setData(Uri.parse("mailto:?subject=" + subject + "&body=" + message));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void exitApp(View view) {
        //Toast.makeText(getApplicationContext(), "Bye, Have a Nice Day!", Toast.LENGTH_SHORT).show();
        finish();
        System.exit(0);
    }
}