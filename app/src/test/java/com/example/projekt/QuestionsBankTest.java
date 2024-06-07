package com.example.projekt;

import com.example.projekt.Repository.QuestionsBank;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class QuestionsBankTest {

    @Before
    public void setUp() throws Exception {
        // Any necessary setup can be done here
    }

    @Test
    public void testJavaQuestionsPL() {
        List<QuestionsList> questions = QuestionsBank.getQuestions("Java");
        assertNotNull(questions);
        assertFalse(questions.isEmpty());
        assertEquals(5, questions.size());
        assertEquals("Który z typów danych w Javie jest uważany za typ prymitywny?", questions.get(0).getQuestion());
    }

    @Test
    public void testJavaQuestionsEN() {
        List<QuestionsList> questions = QuestionsBank.getQuestions("Java");
        assertNotNull(questions);
        assertFalse(questions.isEmpty());
        assertEquals(5, questions.size());
        assertEquals("Which of the following data types in Java is considered a primitive type?", questions.get(0).getQuestion());
    }

    @Test
    public void testAndroidQuestionsPL() {
        List<QuestionsList> questions = QuestionsBank.getQuestions("Android");
        assertNotNull(questions);
        assertFalse(questions.isEmpty());
        assertEquals(5, questions.size());
        assertEquals("Co to jest 'Activity' w Androidzie?", questions.get(0).getQuestion());
    }

    @Test
    public void testAndroidQuestionsEN() {
        List<QuestionsList> questions = QuestionsBank.getQuestions("Android");
        assertNotNull(questions);
        assertFalse(questions.isEmpty());
        assertEquals(5, questions.size());
        assertEquals("What is an 'Activity' in Android?", questions.get(0).getQuestion());
    }

    @Test
    public void testKotlinQuestionsPL() {
        List<QuestionsList> questions = QuestionsBank.getQuestions("Kotlin");
        assertNotNull(questions);
        assertFalse(questions.isEmpty());
        assertEquals(5, questions.size());
        assertEquals("Który z typów danych w Kotlinie jest uważany za typ prymitywny?", questions.get(0).getQuestion());
    }

    @Test
    public void testKotlinQuestionsEN() {
        List<QuestionsList> questions = QuestionsBank.getQuestions("Kotlin");
        assertNotNull(questions);
        assertFalse(questions.isEmpty());
        assertEquals(5, questions.size());
        assertEquals("Which data type in Kotlin is considered a primitive type?", questions.get(0).getQuestion());
    }

    @Test
    public void testCSharpQuestionsPL() {
        List<QuestionsList> questions = QuestionsBank.getQuestions("CSharp");
        assertNotNull(questions);
        assertFalse(questions.isEmpty());
        assertEquals(5, questions.size());
        assertEquals("Który z typów danych w C# jest uważany za typ prymitywny?", questions.get(0).getQuestion());
    }

    @Test
    public void testCSharpQuestionsEN() {
        List<QuestionsList> questions = QuestionsBank.getQuestions("CSharp");
        assertNotNull(questions);
        assertFalse(questions.isEmpty());
        assertEquals(5, questions.size());
        assertEquals("Which data type in C# is considered a primitive type?", questions.get(0).getQuestion());
    }
}
