package com.example.projekt;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.projekt.MainActivity;
import com.example.projekt.R;
import com.example.projekt.Repository.Login;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@RunWith(AndroidJUnit4.class)
public class LoginTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    FirebaseAuth mockAuth;

    @Mock
    FirebaseUser mockUser;

    @Mock
    Task<AuthResult> mockTask;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        when(mockAuth.getCurrentUser()).thenReturn(null);
    }

    @Test
    public void testLogin_Successful() {
        when(mockTask.isSuccessful()).thenReturn(true);
        doAnswer(invocation -> {
            OnCompleteListener<AuthResult> listener = invocation.getArgument(0);
            listener.onComplete(mockTask);
            return null;
        }).when(mockTask).addOnCompleteListener(any());

        when(mockAuth.signInWithEmailAndPassword(anyString(), anyString())).thenReturn(mockTask);

        Context context = ApplicationProvider.getApplicationContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences("MODE", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("night", false);
        editor.apply();

        Intent intent = new Intent(context, Login.class);
        try (ActivityScenario<Login> scenario = ActivityScenario.launch(intent)) {
            scenario.onActivity(activity -> {
                activity.mAuth = mockAuth;

                activity.editTextEmail.setText("test@example.com");
                activity.editTextPassword.setText("password");

                activity.buttonLog.performClick();

            });
        }
    }

    @Test
    public void testLogin_Failure() {
        when(mockTask.isSuccessful()).thenReturn(false);
        doAnswer(invocation -> {
            OnCompleteListener<AuthResult> listener = invocation.getArgument(0);
            listener.onComplete(mockTask);
            return null;
        }).when(mockTask).addOnCompleteListener(any());

        when(mockAuth.signInWithEmailAndPassword(anyString(), anyString())).thenReturn(mockTask);

        Context context = ApplicationProvider.getApplicationContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences("MODE", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("night", false);
        editor.apply();

        Intent intent = new Intent(context, Login.class);
        try (ActivityScenario<Login> scenario = ActivityScenario.launch(intent)) {
            scenario.onActivity(activity -> {
                activity.mAuth = mockAuth;

                activity.editTextEmail.setText("test@example.com");
                activity.editTextPassword.setText("wrongpassword");

                activity.buttonLog.performClick();

            });
        }
    }
}
