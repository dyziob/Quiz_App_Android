package com.example.projekt;
import java.util.Locale;

public class LanguageDetector {

    public static String getSystemLanguage() {
        return Locale.getDefault().getLanguage();
    }
}

