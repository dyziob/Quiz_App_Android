package com.example.projekt;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.window.OnBackInvokedDispatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class QuizActivity extends AppCompatActivity {
    private final OnBackPressedDispatcher onBackPressedDispatcher = getOnBackPressedDispatcher();
    private boolean phoneUsed = false;
    private boolean smsUsed = false;
    private TextView questions;
    private TextView question;
    private AppCompatButton option1, option2, option3, option4;
    private AppCompatButton nextBtn;
    private Timer quizTimer;
    private int TotalTimeMins = 1;
    private int seconds = 0;
    private final List<QuestionsList> questionsLists = new ArrayList<>();
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_quiz);
        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        boolean nightMODE = sharedPreferences.getBoolean("night", false);

        if (nightMODE) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        final ImageView backBtn = findViewById(R.id.backBtn);
        final TextView timer = findViewById(R.id.timer);
        final TextView selectedTopicName = findViewById(R.id.TopicName);

        questions = findViewById(R.id.questions);
        question = findViewById(R.id.question);

        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);

        nextBtn = findViewById(R.id.nextBtn);
        final String getSelectedTopicName = getIntent().getStringExtra("SelectedTopic");

        selectedTopicName.setText(getSelectedTopicName);

        startTimer(timer);

        //back button
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quizTimer.purge();
                quizTimer.cancel();

                startActivity(new Intent(QuizActivity.this, MainActivity.class));
                finish();
            }
        });

        //new onBackPressed
        onBackPressedDispatcher.addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                quizTimer.purge();
                quizTimer.cancel();

                startActivity(new Intent(QuizActivity.this, MainActivity.class));
                finish();
            }
        });

        // Phone Button
        ImageView phoneButton = findViewById(R.id.phoneButton);
        phoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!phoneUsed) {
                    // Open contacts application
                    openContacts();
                    phoneUsed = true;
                } else {
                    Toast.makeText(QuizActivity.this, R.string.PhoneUsed, Toast.LENGTH_SHORT).show();
                }
            }
        });

        // SMS Button
        ImageView smsButton = findViewById(R.id.smsButton);
        smsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!smsUsed) {
                    // Compose a new SMS
                    composeSMS();
                    smsUsed = true;
                } else {
                    Toast.makeText(QuizActivity.this, R.string.SmsUsed, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Method to open contacts application
    private void openContacts() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people"));
        startActivity(intent);
    }

    // Method to compose a new SMS
    private void composeSMS() {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"));
        startActivity(intent);
    }

    //Timer
    private void startTimer(TextView timerTextView){
        quizTimer = new Timer();

        quizTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                if (TotalTimeMins == 0 && seconds == 0) {
                    quizTimer.purge();
                    quizTimer.cancel();

                    Toast.makeText(QuizActivity.this, R.string.time_over, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(QuizActivity.this, QuizResults.class);
                    intent.putExtra("correct", getCorrectAnswers());
                    intent.putExtra("incorrect", getInCorrectAnswers());
                    startActivity(intent);

                    finish();
                } else if (seconds == 0) {
                    TotalTimeMins--;
                    seconds = 59;
                } else {
                    seconds--;
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        String finalMinutes = String.valueOf(TotalTimeMins);
                        String finalSeconds = String.valueOf(seconds);

                        if(finalMinutes.length() == 1){
                            finalMinutes = "0" + finalMinutes;
                        }

                        if(finalSeconds.length() == 1){
                            finalSeconds = "0" + finalSeconds;
                        }

                        timerTextView.setText(finalMinutes + ":" + finalSeconds);
                    }
                });
            }
        },1000,1000);
    }

    private int getCorrectAnswers(){
        int correctAnswers = 0;

        for(int i=0; i<questionsLists.size(); i++){

            final String getUserSelectedAnswer = questionsLists.get(i).getUserSelectedAnswer();
            final String getAnswer = questionsLists.get(i).getAnswer();

            if(getUserSelectedAnswer.equals(getAnswer)){
                correctAnswers++;
            }
        }

        return correctAnswers;
    }

    private int getInCorrectAnswers(){
        int correctAnswers = 0;

        for(int i=0; i<questionsLists.size(); i++){

            final String getUserSelectedAnswer = questionsLists.get(i).getUserSelectedAnswer();
            final String getAnswer = questionsLists.get(i).getAnswer();

            if(!getUserSelectedAnswer.equals(getAnswer)){
                correctAnswers++;
            }
        }

        return correctAnswers;
    }
}
