package com.example.projekt.Repository;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projekt.MainActivity;
import com.example.projekt.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    // Elementy interfejsu użytkownika
    TextInputEditText editTextEmail, editTextPassword;
    Button buttonLog;
    ProgressBar progressBar;
    TextView textView;

    // Firebase Authentication
    FirebaseAuth mAuth;

    // SharedPreferences do przechowywania ustawień aplikacji
    SharedPreferences sharedPreferences;

    @Override
    public void onStart() {
        super.onStart();
        // Sprawdzenie, czy użytkownik jest już zalogowany
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            // Jeśli zalogowany, przekierowanie do MainActivity
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inicjalizacja Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Powiązanie elementów interfejsu użytkownika
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        buttonLog = findViewById(R.id.loginbtn);
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.register_now);

        // Wczytanie preferencji trybu nocnego
        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        boolean nightMODE = sharedPreferences.getBoolean("night", false);

        // Ustawienie trybu nocnego na podstawie preferencji
        if (nightMODE) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        // Ustawienie listenera dla tekstu "Zarejestruj się teraz"
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Przekierowanie do aktywności rejestracji
                Intent intent = new Intent(getApplicationContext(), Registration.class);
                startActivity(intent);
                finish();
            }
        });

        // Ustawienie listenera dla przycisku logowania
        buttonLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE); // Pokazanie paska postępu

                // Pobranie wprowadzonych danych email i hasła
                String email, password;
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());

                // Walidacja email
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Login.this, R.string.Empty_Email, Toast.LENGTH_SHORT).show();
                    return;
                }

                // Walidacja hasła
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Login.this, R.string.password_empty, Toast.LENGTH_SHORT).show();
                    return;
                }

                // Próba zalogowania się za pomocą email i hasła
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE); // Ukrycie paska postępu
                                if (task.isSuccessful()) {
                                    // Sukces logowania, wyświetlenie komunikatu i przekierowanie do MainActivity
                                    Toast.makeText(Login.this, R.string.login_successful, Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // W przypadku niepowodzenia logowania, wyświetlenie komunikatu
                                    Toast.makeText(Login.this, R.string.authentication_failed, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}