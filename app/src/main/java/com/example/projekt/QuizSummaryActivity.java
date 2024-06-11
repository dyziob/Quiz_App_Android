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
    // Deklaracja OnBackPressedDispatcher
    private final OnBackPressedDispatcher onBackPressedDispatcher = getOnBackPressedDispatcher();
    // Deklaracja SharedPreferences
    private SharedPreferences sharedPreferences;

    // Deklaracja AppCompatButton
    private AppCompatButton appCompatButton;

    @SuppressLint("StringFormatInvalid")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Ukrycie action bar
        getSupportActionBar().hide();
        setContentView(R.layout.activity_quiz_summary);

        // Inicjalizacja przycisku powrotu do głównego menu
        appCompatButton = findViewById(R.id.backToMainBtn);

        // Inicjalizacja SharedPreferences
        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        boolean nightMODE = sharedPreferences.getBoolean("night", false);

        // Ustawienie trybu nocnego
        if (nightMODE) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        // Inicjalizacja widoków
        final ImageView backBtn = findViewById(R.id.backBtn);
        final TextView correctAnswersCircle = findViewById(R.id.correctAnswersCircle);
        final TextView correctAnswers = findViewById(R.id.correctAnswers);
        final TextView incorrectAnswers = findViewById(R.id.incorrectAnswers);
        final LinearLayout randomPlayerResults = findViewById(R.id.randomPlayerResults);

        // Pobranie danych z Intent
        Intent intent = getIntent();
        int correct = intent.getIntExtra(getString(R.string.correct), 0);
        int incorrect = intent.getIntExtra(getString(R.string.incorrect), 0);

        // Ustawienie tekstów dla widoków
        correctAnswersCircle.setText(String.valueOf(correct));
        correctAnswers.setText(String.format(getString(R.string.correct_answers), correct));
        incorrectAnswers.setText(String.format(getString(R.string.incorrect_answers), incorrect));

        // Generowanie losowych wyników graczy
        Random random = new Random();
        int numberOfPlayers = 5;
        String[] playerNames = {"Alice", "Bob", "Charlie", "Dave", "Eve"};

        for (int i = 0; i < numberOfPlayers; i++) {
            int randomCorrect = random.nextInt(5);
            int randomIncorrect = 5 - randomCorrect;
            String playerName = playerNames[random.nextInt(playerNames.length)];

            // Tworzenie nowego TextView dla wyniku gracza
            TextView playerResultView = new TextView(this);
            playerResultView.setText(String.format(getString(R.string.player_result), playerName, randomCorrect, randomIncorrect));
            playerResultView.setTextSize(12);
            playerResultView.setPadding(10, 10, 10, 10);
            playerResultView.setTextAppearance(this, R.style.Text);

            // Ustawienie parametrów layoutu
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
            playerResultView.setLayoutParams(params);

            // Dodanie TextView do LinearLayout
            randomPlayerResults.addView(playerResultView);
        }

        // Obsługa kliknięcia przycisku powrotu
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuizSummaryActivity.this, MainActivity.class));
                finish();
            }
        });

        // Obsługa zdarzenia naciśnięcia przycisku wstecz
        onBackPressedDispatcher.addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                startActivity(new Intent(QuizSummaryActivity.this, MainActivity.class));
                finish();
            }
        });

        // Obsługa kliknięcia przycisku powrotu do głównego menu
        appCompatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuizSummaryActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}
