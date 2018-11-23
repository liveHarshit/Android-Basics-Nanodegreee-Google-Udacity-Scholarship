package com.itskshitizsh.myquizapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class PlaygroundActivity extends AppCompatActivity {

    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;

    ImageView img_response1;
    ImageView img_response2;
    ImageView img_response3;
    ImageView img_response4;
    ImageView img_response5;

    TextView textView_response1;
    TextView textView_response2;
    TextView textView_response3;
    TextView textView_response4;
    TextView textView_response5;

    CheckBox checkBox1;
    CheckBox checkBox2;
    CheckBox checkBox3;
    CheckBox checkBox4;

    EditText editText1;
    EditText editText2;

    RadioButton rightResponse11;
    RadioButton rightResponse12;
    RadioButton rightResponse13;
    RadioButton rightResponse14;
    RadioButton rightResponse21;
    RadioButton rightResponse22;

    LinearLayout res_1;
    LinearLayout res_2;
    LinearLayout res_3;
    LinearLayout res_4;
    LinearLayout res_5;


    Button button;
    private String[] answerBank;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playground);

        img_response1 = findViewById(R.id.img_response1);
        img_response2 = findViewById(R.id.img_response2);
        img_response3 = findViewById(R.id.img_response3);
        img_response4 = findViewById(R.id.img_response4);
        img_response5 = findViewById(R.id.img_response5);

        textView_response1 = findViewById(R.id.textView_response1);
        textView_response2 = findViewById(R.id.textView_response2);
        textView_response3 = findViewById(R.id.textView_response3);
        textView_response4 = findViewById(R.id.textView_response4);
        textView_response5 = findViewById(R.id.textView_response5);

        textView1 = findViewById(R.id.question1);
        textView2 = findViewById(R.id.question2);
        textView3 = findViewById(R.id.question3);
        textView4 = findViewById(R.id.question4);
        textView5 = findViewById(R.id.question5);

        rightResponse11 = findViewById(R.id.radioButton1a);
        rightResponse12 = findViewById(R.id.radioButton1b);
        rightResponse13 = findViewById(R.id.radioButton1c);
        rightResponse14 = findViewById(R.id.radioButton1d);
        rightResponse21 = findViewById(R.id.radioButton2a);
        rightResponse22 = findViewById(R.id.radioButton2b);

        checkBox1 = findViewById(R.id.checkbox1);
        checkBox2 = findViewById(R.id.checkbox2);
        checkBox3 = findViewById(R.id.checkbox3);
        checkBox4 = findViewById(R.id.checkbox4);

        editText1 = findViewById(R.id.userResponse1);
        editText2 = findViewById(R.id.userResponse2);

        res_1 = findViewById(R.id.res_1);
        res_2 = findViewById(R.id.res_2);
        res_3 = findViewById(R.id.res_3);
        res_4 = findViewById(R.id.res_4);
        res_5 = findViewById(R.id.res_5);

        Resources resources = getResources();
        String[] questionBank = resources.getStringArray(R.array.questionBank);
        answerBank = resources.getStringArray(R.array.answerBank);

        for (int i = 0; i < 5; i++) {
            questionBank[i] = ("Q." + (i + 1) + " ").concat(questionBank[i]);
        }

        textView1.setText(questionBank[0]);
        textView2.setText(questionBank[1]);
        textView3.setText(questionBank[2]);
        textView4.setText(questionBank[3]);
        textView5.setText(questionBank[4]);

        LinearLayout resButton = findViewById(R.id.buttonResLinearLayout);
        resButton.setVisibility(View.VISIBLE);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Changing Layout After Quiz Completion
                updateLayout();
                //   Submit Button Logic

                score = checkScore();

                Toast.makeText(getApplicationContext(), "Your Score: ".concat(Integer.toString(score)).concat(" out of 5"), Toast.LENGTH_SHORT).show();

                Toast.makeText(getApplicationContext(), "Exit App | Try Again | Share Score\nUsing Icons in the end.", Toast.LENGTH_SHORT).show();

                disableFurtherResponses();
            }
        });

        ImageView quitQuiz = findViewById(R.id.quitQuiz);
        quitQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(PlaygroundActivity.this);
                alertDialogBuilder.setTitle("Exit Quiz App")
                        .setMessage("Are you sure for closing the app?")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getApplicationContext(),"Welcome Back",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getApplicationContext(),"Thanks for Visiting!",Toast.LENGTH_SHORT).show();
                                System.exit(0);
                            }
                        }).create().show();
            }
        });

        ImageView newQuiz = findViewById(R.id.tryAgain);
        newQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(PlaygroundActivity.this);
                alertDialogBuilder.setTitle("Wanna Play Again")
                        .setMessage("Current quiz data will be lost.\nAre you sure to start over?")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // On clicking No, User will see nothing changed.
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getApplicationContext(),"Starting Quiz Again",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(PlaygroundActivity.this,HomeActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }).create().show();
            }
        });
        ImageView shareScore = findViewById(R.id.shareScore);
        shareScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String shareMessage = "My score of Github Quiz is ".concat(Integer.toString(score)).concat(" out of 5.\nYou should try playing and don't forgot to share your score with me and other friends.");
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                startActivity(Intent.createChooser(intent, "Share"));
            }
        });
    }

    private void disableFurtherResponses() {
        // 1
        rightResponse11.setClickable(false);
        rightResponse12.setClickable(false);
        rightResponse13.setClickable(false);
        rightResponse14.setClickable(false);
        // 2
        rightResponse21.setClickable(false);
        rightResponse22.setClickable(false);
        // 3
        checkBox1.setClickable(false);
        checkBox2.setClickable(false);
        checkBox3.setClickable(false);
        checkBox4.setClickable(false);
        // 4
        editText1.setEnabled(false);
        // 5
        editText2.setEnabled(false);

    }


    private void updateLayout() {

        res_1.setVisibility(View.VISIBLE);
        res_2.setVisibility(View.VISIBLE);
        res_3.setVisibility(View.VISIBLE);
        res_4.setVisibility(View.VISIBLE);
        res_5.setVisibility(View.VISIBLE);

        ImageView img_congrats = findViewById(R.id.image_congrats);
        img_congrats.setImageResource(R.drawable.congratulations);
        ImageView shareScore = findViewById(R.id.shareScore);
        shareScore.setImageResource(R.drawable.share);
        ImageView tryAgain = findViewById(R.id.tryAgain);
        tryAgain.setImageResource(R.drawable.try_icon);
        ImageView exit = findViewById(R.id.quitQuiz);
        exit.setImageResource(R.drawable.exit);

        TextView textView = findViewById(R.id.tip);
        textView.setText(getString(R.string.regards));
    }

    private int checkScore() {
        int total = 0;
        if (rightResponse13.isChecked()) {
            total++;
            // Showing Right Answer
            img_response1.setImageResource(R.drawable.smiling);
            textView_response1.setText(getString(R.string.correct));
        } else {
            img_response1.setImageResource(R.drawable.sad);
            textView_response1.setText(getString(R.string.wrong).concat(" ").concat(answerBank[0]));
        }
        if (rightResponse21.isChecked()) {
            total++;
            // Showing Right Answer
            img_response2.setImageResource(R.drawable.smiling);
            textView_response2.setText(getString(R.string.correct));
        } else {
            img_response2.setImageResource(R.drawable.sad);
            textView_response2.setText(getString(R.string.wrong).concat(" ").concat(answerBank[1]));
        }

        if (checkBox1.isChecked() && checkBox2.isChecked() && checkBox3.isChecked() && !(checkBox4.isChecked())) {
            total++;
            // Showing Right Answer
            img_response3.setImageResource(R.drawable.smiling);
            textView_response3.setText(getString(R.string.correct));
        } else {
            img_response3.setImageResource(R.drawable.sad);
            textView_response3.setText(getString(R.string.wrong).concat(" ").concat(answerBank[2]));
        }

        String userResponse1 = editText1.getText().toString();

        if (userResponse1.equals(answerBank[3])) {
            total++;
            // Showing Right Answer
            img_response4.setImageResource(R.drawable.smiling);
            textView_response4.setText(getString(R.string.correct));
        } else {
            img_response4.setImageResource(R.drawable.sad);
            textView_response4.setText(getString(R.string.wrong).concat(" ").concat(answerBank[3]));
        }

        String userResponse2 = editText2.getText().toString();

        if (userResponse2.equals(answerBank[4])) {
            total++;
            // Showing Right Answer
            img_response5.setImageResource(R.drawable.smiling);
            textView_response5.setText(getString(R.string.correct));
        } else {
            img_response5.setImageResource(R.drawable.sad);
            textView_response5.setText(getString(R.string.wrong).concat(" ").concat(answerBank[4]));
        }
        return total;
    }
}