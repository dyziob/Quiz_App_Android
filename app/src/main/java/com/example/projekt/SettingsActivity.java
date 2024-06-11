package com.example.projekt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.projekt.Repository.Login;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SettingsActivity extends AppCompatActivity {

    // Deklaracje zmiennych dla Firebase, przycisków, widoków tekstowych i przełącznika
    FirebaseAuth auth;
    TextView textView;
    FirebaseUser user;
    Button log_button;
    Switch darkModeSwitch;
    boolean nightMODE;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        // Ukrycie action bar
        getSupportActionBar().hide();
        final ImageView backBtn = findViewById(R.id.backBtn);

        // Inicjalizacja FirebaseAuth
        auth = FirebaseAuth.getInstance();
        log_button = findViewById(R.id.logoutButton);
        textView = findViewById(R.id.user_details);
        darkModeSwitch = findViewById(R.id.darkModeSwitch);
        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        nightMODE = sharedPreferences.getBoolean("night", false);

        // Pobranie aktualnego użytkownika
        user = auth.getCurrentUser();
        if (user == null) {
            // Jeśli użytkownik nie jest zalogowany, przejście do ekranu logowania
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        } else {
            // Ustawienie adresu email użytkownika w widoku tekstowym
            textView.setText(user.getEmail());
        }

        // Obsługa kliknięcia przycisku powrotu
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingsActivity.this, MainActivity.class));
                finish();
            }
        });

        // Obsługa kliknięcia przycisku wylogowania
        log_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Ustawienie przełącznika trybu nocnego na podstawie zapisanego stanu
        if(nightMODE){
            darkModeSwitch.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        // Obsługa kliknięcia przełącznika trybu nocnego
        darkModeSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Zmiana trybu nocnego i zapisanie stanu w SharedPreferences
                if(nightMODE){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("night", false);
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("night", true);
                }
                editor.apply();

                // Powiadomienie o zmianie trybu nocnego
                Intent resultIntent = new Intent();
                resultIntent.putExtra("nightModeChanged", true);
                setResult(Activity.RESULT_OK, resultIntent);
            }
        });

    }
}
