package com.example.projekt;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projekt.Repository.QuestionsBank;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class QuizActivity extends AppCompatActivity {
    private final OnBackPressedDispatcher onBackPressedDispatcher = getOnBackPressedDispatcher();
    private boolean phoneUsed = false;
    private boolean smsUsed = false;
    private TextView questions_number;
    private TextView question;
    private AppCompatButton option1, option2, option3, option4;
    private AppCompatButton nextBtn;
    private Timer quizTimer;
    private int TotalTimeMins = 1;
    private int seconds = 0;
    private List<QuestionsList> questionsLists;
    SharedPreferences sharedPreferences;
    private int currnetQuestionPosition = 0;
    private String selectedOptionByUser = "";

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

        questions_number = findViewById(R.id.questions);
        question = findViewById(R.id.question);

        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);

        nextBtn = findViewById(R.id.nextBtn);
        final String getSelectedTopicName = getIntent().getStringExtra("SelectedTopic");

        // Ustawienie nazwy wybranego tematu
        selectedTopicName.setText(getSelectedTopicName);
        questionsLists = QuestionsBank.getQuestions(getSelectedTopicName);

        // Uruchomienie timera
        startTimer(timer);

        // Ustawienie pierwszego pytania i opcji odpowiedzi
        questions_number.setText((currnetQuestionPosition+1)+"/"+questionsLists.size());
        question.setText(questionsLists.get(currnetQuestionPosition).getQuestion());
        option1.setText(questionsLists.get(currnetQuestionPosition).getOption1());
        option2.setText(questionsLists.get(currnetQuestionPosition).getOption2());
        option3.setText(questionsLists.get(currnetQuestionPosition).getOption3());
        option4.setText(questionsLists.get(currnetQuestionPosition).getOption4());

        // Obsługa kliknięcia na opcję 1
        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedOptionByUser.isEmpty()) {
                    selectedOptionByUser = option1.getText().toString();

                    option1.setBackgroundResource(R.drawable.round_back_red10);
                    option1.setTextColor(Color.WHITE);

                    revealAnswer();

                    questionsLists.get(currnetQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });

        // Obsługa kliknięcia na opcję 2
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedOptionByUser.isEmpty()) {
                    selectedOptionByUser = option2.getText().toString();

                    option2.setBackgroundResource(R.drawable.round_back_red10);
                    option2.setTextColor(Color.WHITE);

                    revealAnswer();

                    questionsLists.get(currnetQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });

        // Obsługa kliknięcia na opcję 3
        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedOptionByUser.isEmpty()) {
                    selectedOptionByUser = option3.getText().toString();

                    option3.setBackgroundResource(R.drawable.round_back_red10);
                    option3.setTextColor(Color.WHITE);

                    revealAnswer();

                    questionsLists.get(currnetQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });

        // Obsługa kliknięcia na opcję 4
        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedOptionByUser.isEmpty()) {
                    selectedOptionByUser = option4.getText().toString();

                    option4.setBackgroundResource(R.drawable.round_back_red10);
                    option4.setTextColor(Color.WHITE);

                    revealAnswer();

                    questionsLists.get(currnetQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });

        // Obsługa kliknięcia przycisku "Dalej"
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedOptionByUser.isEmpty()){
                    Toast.makeText(QuizActivity.this, getString(R.string.NoAnswerSelect), Toast.LENGTH_SHORT).show();
                }
                else {
                    changeNextQuestions();
                }
            }
        });

        // Obsługa kliknięcia przycisku "Wstecz"
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quizTimer.purge();
                quizTimer.cancel();

                startActivity(new Intent(QuizActivity.this, MainActivity.class));
                finish();
            }
        });

        // Nowa obsługa przycisku "Wstecz"
        onBackPressedDispatcher.addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                quizTimer.purge();
                quizTimer.cancel();

                startActivity(new Intent(QuizActivity.this, MainActivity.class));
                finish();
            }
        });

        // Przycisk telefonu
        ImageView phoneButton = findViewById(R.id.phoneButton);
        phoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!phoneUsed) {
                    // Otwórz aplikację kontaktów
                    openContacts();
                    phoneUsed = true;
                } else {
                    Toast.makeText(QuizActivity.this, R.string.PhoneUsed, Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Przycisk SMS
        ImageView smsButton = findViewById(R.id.smsButton);
        smsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!smsUsed) {
                    // Komponuj nowy SMS
                    composeSMS();
                    smsUsed = true;
                } else {
                    Toast.makeText(QuizActivity.this, R.string.SmsUsed, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Metoda otwierająca aplikację kontaktów
    private void openContacts() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people"));
        startActivity(intent);
    }

    // Metoda komponująca nowy SMS
    private void composeSMS() {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"));
        startActivity(intent);
    }

    // Metoda zmieniająca pytanie na następne
    private void changeNextQuestions(){
        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        boolean nightMODE = sharedPreferences.getBoolean("night", false);
        currnetQuestionPosition++;

        if((currnetQuestionPosition+1) == questionsLists.size()){
            nextBtn.setText(getString(R.string.Submit));
        }

        if(currnetQuestionPosition < questionsLists.size()){
            selectedOptionByUser = "";

            if (nightMODE) {
                option1.setBackgroundResource(R.drawable.round_back_dark_stroke2_10);
                option1.setTextColor(Color.WHITE);

                option2.setBackgroundResource(R.drawable.round_back_dark_stroke2_10);
                option2.setTextColor(Color.WHITE);

                option3.setBackgroundResource(R.drawable.round_back_dark_stroke2_10);
                option3.setTextColor(Color.WHITE);

                option4.setBackgroundResource(R.drawable.round_back_dark_stroke2_10);
                option4.setTextColor(Color.WHITE);
            } else {
                option1.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
                option1.setTextColor(Color.BLACK);

                option2.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
                option2.setTextColor(Color.BLACK);

                option3.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
                option3.setTextColor(Color.BLACK);

                option4.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
                option4.setTextColor(Color.BLACK);
            }

            // Ustawienie kolejnego pytania i opcji odpowiedzi
            questions_number.setText((currnetQuestionPosition+1)+"/"+questionsLists.size());
            question.setText(questionsLists.get(currnetQuestionPosition).getQuestion());
            option1.setText(questionsLists.get(currnetQuestionPosition).getOption1());
            option2.setText(questionsLists.get(currnetQuestionPosition).getOption2());
            option3.setText(questionsLists.get(currnetQuestionPosition).getOption3());
            option4.setText(questionsLists.get(currnetQuestionPosition).getOption4());
        }
        else{
            quizTimer.purge();
            quizTimer.cancel();
            Intent intent = new Intent(QuizActivity.this, QuizSummaryActivity.class);
            intent.putExtra(getString(R.string.correct), getCorrectAnswers());
            intent.putExtra(getString(R.string.incorrect), getInCorrectAnswers());
            startActivity(intent);

            finish();
        }
    }

    // Timer
    private void startTimer(TextView timerTextView){
        quizTimer = new Timer();

        quizTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                if (TotalTimeMins == 0 && seconds == 0) {
                    quizTimer.purge();
                    quizTimer.cancel();

                    Toast.makeText(QuizActivity.this, R.string.time_over, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(QuizActivity.this, QuizSummaryActivity.class);
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

    // Metoda licząca poprawne odpowiedzi
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

    // Metoda licząca niepoprawne odpowiedzi
    private int getInCorrectAnswers(){
        int incorrectAnswers = 0;

        for(int i=0; i<questionsLists.size(); i++){
            final String getUserSelectedAnswer = questionsLists.get(i).getUserSelectedAnswer();
            final String getAnswer = questionsLists.get(i).getAnswer();

            if(!getUserSelectedAnswer.equals(getAnswer)){
                incorrectAnswers++;
            }
        }

        return incorrectAnswers;
    }

    // Metoda ujawniająca poprawną odpowiedź
    private void revealAnswer() {
        final String getAnswer = questionsLists.get(currnetQuestionPosition).getAnswer();
        if (option1.getText().toString().equals(getAnswer)) {
            option1.setBackgroundResource(R.drawable.round_back_green10);
            option1.setTextColor(Color.WHITE);
        }
        else if (option2.getText().toString().equals(getAnswer)) {
            option2.setBackgroundResource(R.drawable.round_back_green10);
            option2.setTextColor(Color.WHITE);
        }
        else if (option3.getText().toString().equals(getAnswer)) {
            option3.setBackgroundResource(R.drawable.round_back_green10);
            option3.setTextColor(Color.WHITE);
        }
        else if (option4.getText().toString().equals(getAnswer)) {
            option4.setBackgroundResource(R.drawable.round_back_green10);
            option4.setTextColor(Color.WHITE);
        }
    }
}