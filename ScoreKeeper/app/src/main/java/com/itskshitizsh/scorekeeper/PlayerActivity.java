package com.itskshitizsh.scorekeeper;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PlayerActivity extends AppCompatActivity {

    Button button;
    EditText a_player1;
    EditText a_player2;
    EditText b_player1;
    EditText b_player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        a_player1 = findViewById(R.id.a_player1);
        a_player2 = findViewById(R.id.a_player2);
        b_player1 = findViewById(R.id.b_player1);
        b_player2 = findViewById(R.id.b_player2);
    }
    public void displayRules(View view)
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(PlayerActivity.this);
        alertDialogBuilder.setTitle("Rules Set").setMessage( getString(R.string.rules)).setCancelable(false).setNeutralButton("Agree", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "Good Luck! Enjoy Playing!", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    public void startPlay(View view)
    {
        button = findViewById(R.id.defaults);
        switch (view.getId())
        {
            case R.id.defaults:
                Intent intent_quick = new Intent(PlayerActivity.this,MainActivity.class);
                startActivity(intent_quick);
                finish();
                break;
            case R.id.startPlaying:
                String aPlayer1Name = a_player1.getText().toString().trim();
                String bPlayer1Name = b_player1.getText().toString().trim();
                if (aPlayer1Name.isEmpty())
                {
                    a_player1.setError("Player Name Required");
                    a_player1.requestFocus();
                    break;
                }
                if (bPlayer1Name.isEmpty())
                {
                    b_player1.setError("Player Name Required");
                    b_player1.requestFocus();
                    break;
                }
                String aPlayer2Name = a_player2.getText().toString().trim();
                String bPlayer2Name = b_player2.getText().toString().trim();
                if((!aPlayer2Name.isEmpty())&&bPlayer2Name.isEmpty())
                {
                    b_player2.setError("Player Name Required");
                    b_player2.requestFocus();
                    break;
                }
                if ((!bPlayer2Name.isEmpty())&&aPlayer2Name.isEmpty())
                {
                    a_player2.setError("Player Name Required");
                    a_player2.requestFocus();
                    break;
                }else if(aPlayer2Name.isEmpty())
                {
                    //aPlayer2Name = getString(R.string.secondPlayerA);
                    //bPlayer2Name = getString(R.string.secondPlayerB);
                    aPlayer2Name = "-------";
                    bPlayer2Name = "-------";
                }
                String[] str = {aPlayer1Name,bPlayer1Name,aPlayer2Name,bPlayer2Name};
                Intent intent = new Intent(PlayerActivity.this,MainActivity.class);
                intent.putExtra("playerName",str);
                startActivity(intent);
                finish();
                /* format     Team   A      Team B              PlayerName: Index
                *            Player 1:1   Player 1:2
                *            Player 2:3   Player 2:4
                */
                break;
            default:break;
        }
    }
}
