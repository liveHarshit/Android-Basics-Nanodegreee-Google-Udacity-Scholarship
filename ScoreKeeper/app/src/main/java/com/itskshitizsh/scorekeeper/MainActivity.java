package com.itskshitizsh.scorekeeper;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int score_of_A = 0;
    private int score_of_B = 0;

    TextView scoreOf_A;
    TextView scoreOf_B;

    TextView A_player1Name;
    TextView A_player2Name;
    TextView B_player1Name;
    TextView B_player2Name;

    boolean flag;
    boolean flagA, flagB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreOf_A = findViewById(R.id.scoreOfA);
        scoreOf_B = findViewById(R.id.scoreOfB);
        flagA = false;
        flagB = false;
        display();

        A_player1Name = findViewById(R.id.player1Name_A);
        A_player2Name = findViewById(R.id.player2Name_A);
        B_player1Name = findViewById(R.id.player1Name_B);
        B_player2Name = findViewById(R.id.player2Name_B);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String[] playerNames = extras.getStringArray("playerName");
            if (playerNames != null) {
                A_player1Name.setText(playerNames[0]);
                A_player2Name.setText(playerNames[2]);
                B_player1Name.setText(playerNames[1]);
                B_player2Name.setText(playerNames[3]);
            }
        } else {
            A_player1Name.setText(getString(R.string.firstPlayerA));
            A_player2Name.setText(getString(R.string.secondPlayerA));
            B_player1Name.setText(getString(R.string.firstPlayerB));
            B_player2Name.setText(getString(R.string.secondPlayerB));
        }
    }

    public void click_on_img(View view) {
        switch (view.getId()) {
            case R.id.reset:
                score_of_A = 0;
                score_of_B = 0;
                display();
                break;

            case R.id.exit:
                System.exit(0);
                break;

            case R.id.new_img:
                startActivity(new Intent(MainActivity.this, PlayerActivity.class));
                finish();
                break;

            case R.id.undo:
                if (flagA) {
                    score_of_A--;
                    flagA = false;
                    display();
                } else if (flagB) {
                    if (score_of_B > 0) {
                        score_of_B--;
                        flagB = false;
                        display();
                    }
                }
                break;
            default:
                break;
        }
    }

    public void display() {
        scoreOf_A.setText(String.valueOf(score_of_A));
        scoreOf_B.setText(String.valueOf(score_of_B));
    }

    /* Score Logic */

    public void incrementA(View view) {
        flag = check();
        if (flag) {
            if (score_of_A >= 0 && score_of_A < 30) {
                score_of_A++;
                flagA = true;
                flagB = false;
                display();
                flag = check();
            } else {
                if(score_of_A==30)
                {
                    alertResult(getString(R.string.teamA));
                }
                flag = false;
            }
        } else {
            Toast.makeText(getApplicationContext(), "Try Reset Values or Start New Game!\n Game Over", Toast.LENGTH_LONG).show();
            flag = false;
        }
    }

    public void incrementB(View view) {
        flag = check();
        if (flag) {
            if (score_of_B >= 0 && score_of_B < 30 && score_of_A != 30) {
                score_of_B++;
                flagB = true;
                flagA = false;
                display();
                flag = check();
            } else {
                if(score_of_B==30)
                {
                    alertResult(getString(R.string.teamB));
                }
                flag = false;
            }
        } else {
            Toast.makeText(getApplicationContext(), "Try Reset Values or Start New Game!\n Game Over", Toast.LENGTH_LONG).show();
            flag = false;
        }
    }

    public void alertResult(String str) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setTitle("Match Result").setMessage(str + " Wins, Congratulations !").setCancelable(false).setNeutralButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "Game Over! Try New!", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    /*
        check checks whether any team WINS the Match.
        and returns boolean value saying, is there a need to continue pointing teams?
     */
    private boolean check() {
        boolean bool = true;

        if (score_of_A == 21 && score_of_B < 20) {
            // A Wins
            alertResult(getString(R.string.teamA));
            bool = false;
        } else if (score_of_B == 21 && score_of_A < 20) {
            alertResult(getString(R.string.teamB));
            bool = false;
        } else if ((score_of_A > 21 && score_of_A < 30) || (score_of_B > 21 && score_of_B < 30)) {
            if ((score_of_A - score_of_B) == 2) {
                // Team A Wins
                alertResult(getString(R.string.teamA));
                bool = false;
            } else {
                if ((score_of_B - score_of_A) == 2) {
                    // Team B Wins
                    alertResult(getString(R.string.teamB));
                    bool = false;
                }
            }
        } else if (score_of_A == 30 && score_of_B < 30) {
            // Team A Wins
            alertResult(getString(R.string.teamA));
            bool = false;
        } else if (score_of_B == 30 && score_of_A < 30) {
            // Team B Wins
            alertResult(getString(R.string.teamB));
            bool = false;
        }
        return bool;
    }
    private boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this,"Hit back again to Exit!\n Waring Game will be Reset!", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}