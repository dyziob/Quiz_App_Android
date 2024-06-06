package com.example.projekt;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;

import java.util.Random;

public class QuizSummaryActivity extends AppCompatActivity {
    private final OnBackPressedDispatcher onBackPressedDispatcher = getOnBackPressedDispatcher();
    private SharedPreferences sharedPreferences;

    private AppCompatButton appCompatButton;

    @SuppressLint("StringFormatInvalid")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_quiz_summary);

        appCompatButton = findViewById(R.id.backToMainBtn);

        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        boolean nightMODE = sharedPreferences.getBoolean("night", false);

        if (nightMODE) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        final ImageView backBtn = findViewById(R.id.backBtn);
        final TextView correctAnswersCircle = findViewById(R.id.correctAnswersCircle);
        final TextView correctAnswers = findViewById(R.id.correctAnswers);
        final TextView incorrectAnswers = findViewById(R.id.incorrectAnswers);
        final LinearLayout randomPlayerResults = findViewById(R.id.randomPlayerResults);

        Intent intent = getIntent();
        int correct = intent.getIntExtra(getString(R.string.correct), 0);
        int incorrect = intent.getIntExtra(getString(R.string.incorrect), 0);

        correctAnswersCircle.setText(String.valueOf(correct));
        correctAnswers.setText(String.format(getString(R.string.correct_answers), correct));
        incorrectAnswers.setText(String.format(getString(R.string.incorrect_answers), incorrect));

        Random random = new Random();
        int numberOfPlayers = 5;
        String[] playerNames = {"Alice", "Bob", "Charlie", "Dave", "Eve"};

        for (int i = 0; i < numberOfPlayers; i++) {
            int randomCorrect = random.nextInt(5);
            int randomIncorrect = 5 - randomCorrect;
            String playerName = playerNames[random.nextInt(playerNames.length)];

            TextView playerResultView = new TextView(this);
            playerResultView.setText(String.format(getString(R.string.player_result), playerName, randomCorrect, randomIncorrect));
            playerResultView.setTextSize(12);
            playerResultView.setPadding(10, 10, 10, 10);
            //playerResultView.setTextColor(getResources().getColor(R.color.black));
            playerResultView.setTextAppearance(this, R.style.Text);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
            playerResultView.setLayoutParams(params);

            randomPlayerResults.addView(playerResultView);
        }

        // Back button
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuizSummaryActivity.this, MainActivity.class));
                finish();
            }
        });

        // OnBackPressed
        onBackPressedDispatcher.addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                startActivity(new Intent(QuizSummaryActivity.this, MainActivity.class));
                finish();
            }
        });

        appCompatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuizSummaryActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}
