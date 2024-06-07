package com.example.projekt;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.test.core.app.ApplicationProvider;
import androidx.appcompat.app.AppCompatDelegate;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RegistrationTest {
    private SharedPreferences sharedPreferences;
    private Context context;

    @Before
    public void setUp() {
        context = ApplicationProvider.getApplicationContext();
        sharedPreferences = context.getSharedPreferences("MODE", Context.MODE_PRIVATE);
    }

    @Test
    public void testNightModeEnabled() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("night", true);
        editor.apply();

        boolean nightMode = sharedPreferences.getBoolean("night", false);
        assertTrue(nightMode);
        assertEquals(AppCompatDelegate.MODE_NIGHT_YES, AppCompatDelegate.getDefaultNightMode());
    }

    @Test
    public void testNightModeDisabled() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("night", false);
        editor.apply();

        boolean nightMode = sharedPreferences.getBoolean("night", false);
        assertFalse(nightMode);
        assertEquals(AppCompatDelegate.MODE_NIGHT_NO, AppCompatDelegate.getDefaultNightMode());
    }
}
