package com.example.projekt;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.test.core.app.ApplicationProvider;

import com.example.projekt.Repository.Login;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(RobolectricTestRunner.class)
public class SettingsActivityTest {

    private FirebaseAuth mockAuth;
    private FirebaseUser mockUser;
    private SharedPreferences sharedPreferences;
    private SettingsActivity settingsActivity;

    @Before
    public void setUp() {
        // Mock FirebaseAuth and FirebaseUser
        mockAuth = Mockito.mock(FirebaseAuth.class);
        mockUser = Mockito.mock(FirebaseUser.class);
        when(mockAuth.getCurrentUser()).thenReturn(mockUser);
        when(mockUser.getEmail()).thenReturn("test@example.com");

        // Initialize SharedPreferences with default night mode off
        sharedPreferences = ApplicationProvider.getApplicationContext().getSharedPreferences("MODE", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("night", false);
        editor.apply();

        // Create the activity
        ActivityController<SettingsActivity> controller = Robolectric.buildActivity(SettingsActivity.class);
        settingsActivity = controller.create().start().resume().get();
    }

    @Test
    public void testNightMode() {
        // Set night mode in SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("night", true);
        editor.apply();

        // Verify night mode is set in SharedPreferences
        assertTrue(sharedPreferences.getBoolean("night", false));
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        // Recreate the activity to apply the new night mode
        settingsActivity.recreate();

        // Verify night mode is applied
        assertEquals(AppCompatDelegate.MODE_NIGHT_YES, AppCompatDelegate.getDefaultNightMode());
    }

    @Test
    public void testUserLoggedIn() {
        // Verify the user email is displayed
        TextView textView = settingsActivity.findViewById(R.id.user_details);
        assertEquals("test@example.com", textView.getText().toString());
    }

    @Test
    public void testUserLoggedOut() {
        // Mock no user logged in
        when(mockAuth.getCurrentUser()).thenReturn(null);

        // Recreate the activity to apply the new auth state
        settingsActivity.recreate();

        // Verify the activity starts Login activity when user is logged out
        Intent expectedIntent = new Intent(settingsActivity, Login.class);
        assertEquals(expectedIntent.getComponent(), settingsActivity.getIntent().getComponent());
    }
}
