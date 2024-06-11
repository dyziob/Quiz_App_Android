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

import com.example.projekt.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registration extends AppCompatActivity {

    // Elementy interfejsu użytkownika
    TextInputEditText editTextEmail, editTextPassword;
    Button buttonReg;
    ProgressBar progressBar;
    TextView textView;

    // Firebase Authentication
    FirebaseAuth mAuth;

    // SharedPreferences do przechowywania ustawień aplikacji
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Inicjalizacja Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Powiązanie elementów interfejsu użytkownika
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        buttonReg = findViewById(R.id.registerbtn);
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.login_now);

        // Wczytanie preferencji trybu nocnego
        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        boolean nightMODE = sharedPreferences.getBoolean("night", false);

        // Ustawienie trybu nocnego na podstawie preferencji
        if (nightMODE) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        // Ustawienie listenera dla tekstu "Zaloguj się teraz"
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Przekierowanie do aktywności logowania
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        // Ustawienie listenera dla przycisku rejestracji
        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE); // Pokazanie paska postępu

                // Pobranie wprowadzonych danych email i hasła
                String email, password;
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());

                // Walidacja email
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Registration.this, R.string.Empty_Email, Toast.LENGTH_SHORT).show();
                    return;
                }

                // Walidacja hasła
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Registration.this, R.string.password_empty, Toast.LENGTH_SHORT).show();
                    return;
                }

                // Próba utworzenia konta za pomocą email i hasła
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE); // Ukrycie paska postępu

                                if (task.isSuccessful()) {
                                    // Sukces rejestracji, wyświetlenie komunikatu i przekierowanie do aktywności logowania
                                    Toast.makeText(Registration.this, getString(R.string.account_created),
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), Login.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // W przypadku niepowodzenia rejestracji, wyświetlenie komunikatu
                                    Toast.makeText(Registration.this, R.string.authentication_failed,
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
