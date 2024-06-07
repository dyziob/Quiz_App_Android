package com.example.projekt;

import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.projekt.Repository.QuestionsBank;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class QuizActivityTest {

    private List<QuestionsList> mockQuestionsList;

    @Before
    public void setUp() {
        mockQuestionsList = new ArrayList<>();
        mockQuestionsList.add(new QuestionsList("Question 1", "Option 1", "Option 2", "Option 3", "Option 4", "Option 1", ""));
        mockQuestionsList.add(new QuestionsList("Question 2", "Option 1", "Option 2", "Option 3", "Option 4", "Option 2", ""));
        mockQuestionsList.add(new QuestionsList("Question 3", "Option 1", "Option 2", "Option 3", "Option 4", "Option 3", ""));

        // Mock the QuestionsBank to return the mockQuestionsList
        QuestionsBank mockQuestionsBank = Mockito.mock(QuestionsBank.class);
        when(mockQuestionsBank.getQuestions(Mockito.anyString())).thenReturn(mockQuestionsList);
    }

    @Test
    public void testQuizActivity() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.putExtra("SelectedTopic", "Test Topic");

        try (ActivityScenario<QuizActivity> scenario = ActivityScenario.launch(intent)) {
            onView(withId(R.id.question)).check(matches(withText("Question 1")));

            onView(withId(R.id.option1)).perform(click());
            onView(withId(R.id.nextBtn)).perform(click());

            onView(withId(R.id.question)).check(matches(withText("Question 2")));

            onView(withId(R.id.option2)).perform(click());
            onView(withId(R.id.nextBtn)).perform(click());

            onView(withId(R.id.question)).check(matches(withText("Question 3")));

            onView(withId(R.id.option3)).perform(click());
            onView(withId(R.id.nextBtn)).perform(click());

            // onView(withId(R.id.quizSummaryActivity)).check(matches(isDisplayed()));
        }
    }

    @Test
    public void testTimer() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.putExtra("SelectedTopic", "Test Topic");

        try (ActivityScenario<QuizActivity> scenario = ActivityScenario.launch(intent)) {
            onView(withId(R.id.timer)).check(matches(isDisplayed()));

            Thread.sleep(2000);

            onView(withId(R.id.timer)).check(matches(withText("00:58")));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
