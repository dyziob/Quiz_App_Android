package com.example.projekt.Services;

import com.example.projekt.QuestionsList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public class JSONUtils {

    private static final Gson gson = new Gson();

    public static String toJSON(List<QuestionsList> questions) {
        return gson.toJson(questions);
    }

    public static List<QuestionsList> fromJSON(String jsonString) {
        Type questionListType = new TypeToken<List<QuestionsList>>(){}.getType();
        return gson.fromJson(jsonString, questionListType);
    }
}

