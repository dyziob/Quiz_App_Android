package com.example.projekt.Repository;

import com.example.projekt.LanguageDetector;
import com.example.projekt.QuestionsList;

import java.util.ArrayList;
import java.util.List;

public class QuestionsBank {

    private static List<QuestionsList> javaQuestionsPL(){
        final List<QuestionsList> questionsLists = new ArrayList<>();

        final QuestionsList question1 = new QuestionsList("Który z typów danych w Javie jest uważany za typ prymitywny?", "Integer", "String", "int", "Double", "int", "");
        final QuestionsList question2 = new QuestionsList("Co oznacza 'Java Virtual Machine' (JVM)?", "Jest to kompilator Javy.", "Jest to zestaw narzędzi do programowania w Javie.", "Jest to środowisko wykonawcze, które wykonuje kod Javy.", "Jest to środowisko programistyczne dla Javy.", "Jest to środowisko wykonawcze, które wykonuje kod Javy.", "");
        final QuestionsList question3 = new QuestionsList("Które z poniższych słów kluczowych jest używane do definiowania klas w Javie?", "class", "type", "struct", "def", "class", "");
        final QuestionsList question4 = new QuestionsList("Która z poniższych metod jest wywoływana automatycznie podczas tworzenia obiektu klasy?", "start()", "run()", "init()", "constructor()", "constructor()", "");
        final QuestionsList question5 = new QuestionsList("Która instrukcja jest używana do ręcznego zgłaszania wyjątku w Javie?", "throw", "raise", "except", "try", "throw", "");

        questionsLists.add(question1);
        questionsLists.add(question2);
        questionsLists.add(question3);
        questionsLists.add(question4);
        questionsLists.add(question5);

        return questionsLists;
    }

    private static List<QuestionsList> javaQuestionsEN(){
        final List<QuestionsList> questionsLists = new ArrayList<>();

        final QuestionsList question1 = new QuestionsList("Which of the following data types in Java is considered a primitive type?", "Integer", "String", "int", "Double", "int", "");
        final QuestionsList question2 = new QuestionsList("What does 'Java Virtual Machine' (JVM) mean?", "It is a Java compiler.", "It is a set of tools for programming in Java.", "It is a runtime environment that executes Java code.", "It is a development environment for Java.", "It is a runtime environment that executes Java code.", "");
        final QuestionsList question3 = new QuestionsList("Which of the following keywords is used to define classes in Java?", "class", "type", "struct", "def", "class", "");
        final QuestionsList question4 = new QuestionsList("Which of the following methods is automatically called when an object of a class is created?", "start()", "run()", "init()", "constructor()", "constructor()", "");
        final QuestionsList question5 = new QuestionsList("Which statement is used to manually throw an exception in Java?", "throw", "raise", "except", "try", "throw", "");

        questionsLists.add(question1);
        questionsLists.add(question2);
        questionsLists.add(question3);
        questionsLists.add(question4);
        questionsLists.add(question5);

        return questionsLists;
    }

    private static List<QuestionsList> androidQuestionsPL(){
        final List<QuestionsList> questionsLists = new ArrayList<>();

        final QuestionsList question1 = new QuestionsList("Co to jest 'Activity' w Androidzie?", "Jednostka kodu reprezentująca ekran z interfejsem użytkownika.", "Plik konfiguracyjny aplikacji.", "Mechanizm debugowania.", "Komponent zarządzający bazami danych.", "Jednostka kodu reprezentująca ekran z interfejsem użytkownika.", "");
        final QuestionsList question2 = new QuestionsList("Co to jest 'Intent' w Androidzie?", "Mechanizm przesyłania danych między komponentami.", "Interfejs projektowania interfejsu użytkownika.", "Komponent do łączenia z zasobami internetowymi.", "Klasa zarządzająca bazami danych.", "Mechanizm przesyłania danych między komponentami.", "");
        final QuestionsList question3 = new QuestionsList("Co to jest 'Layout' w Androidzie?", "Struktura określająca układ i wygląd interfejsu użytkownika.", "Plik konfiguracyjny uprawnień aplikacji.", "Narzędzie do debugowania interfejsu.", "Zbiór klas i interfejsów do tworzenia interfejsu.", "Struktura określająca układ i wygląd interfejsu użytkownika.", "");
        final QuestionsList question4 = new QuestionsList("Co to jest 'Fragment' w Androidzie?", "Moduł aplikacji używany w różnych aktywnościach.", "Klasa reprezentująca ekran interfejsu użytkownika.", "Komponent do komunikacji z serwerami Firebase.", "Komponent przetwarzający dane.", "Moduł aplikacji używany w różnych aktywnościach.", "");
        final QuestionsList question5 = new QuestionsList("Co to jest 'Manifest' w Androidzie?", "Plik konfiguracyjny aplikacji zawierający informacje o aplikacji.", "Komponent wyświetlający komunikaty użytkownikowi.", "Narzędzie do debugowania aplikacji.", "Klasa zarządzająca sesją użytkownika.", "Plik konfiguracyjny aplikacji zawierający informacje o aplikacji.", "");

        questionsLists.add(question1);
        questionsLists.add(question2);
        questionsLists.add(question3);
        questionsLists.add(question4);
        questionsLists.add(question5);

        return questionsLists;
    }

    private static List<QuestionsList> androidQuestionsEN(){
        final List<QuestionsList> questionsLists = new ArrayList<>();

        final QuestionsList question1 = new QuestionsList("What is an 'Activity' in Android?", "A unit of code representing a screen with a user interface.", "An application configuration file.", "A debugging mechanism.", "A component managing databases.", "A unit of code representing a screen with a user interface.", "");
        final QuestionsList question2 = new QuestionsList("What is an 'Intent' in Android?", "A mechanism for transferring data between components.", "An interface design interface.", "A component for connecting to web resources.", "A class managing databases.", "A mechanism for transferring data between components.", "");
        final QuestionsList question3 = new QuestionsList("What is a 'Layout' in Android?", "A structure defining the layout and appearance of the user interface.", "An application permissions configuration file.", "A debugging tool for the interface.", "A set of classes and interfaces for creating an interface.", "A structure defining the layout and appearance of the user interface.", "");
        final QuestionsList question4 = new QuestionsList("What is a 'Fragment' in Android?", "An application module used in various activities.", "A class representing a user interface screen.", "A component for communicating with Firebase servers.", "A data processing component.", "An application module used in various activities.", "");
        final QuestionsList question5 = new QuestionsList("What is a 'Manifest' in Android?", "An application configuration file containing information about the application.", "A component displaying messages to the user.", "An application debugging tool.", "A class managing user sessions.", "An application configuration file containing information about the application.", "");

        questionsLists.add(question1);
        questionsLists.add(question2);
        questionsLists.add(question3);
        questionsLists.add(question4);
        questionsLists.add(question5);

        return questionsLists;
    }

    private static List<QuestionsList> kotlinQuestionsPL(){

        final List<QuestionsList> questionsLists = new ArrayList<>();

        final QuestionsList question1 = new QuestionsList("Który z typów danych w Kotlinie jest uważany za typ prymitywny?", "Int", "String", "Integer", "Double", "Int", "");
        final QuestionsList question2 = new QuestionsList("Który operator jest używany do porównywania dwóch wartości w Kotlinie?", "==", "=", "!=", "<>", "==", "");
        final QuestionsList question3 = new QuestionsList("Która z poniższych kolekcji w Kotlinie zapewnia szybkie wstawianie i pobieranie elementów?", "ArrayList", "LinkedList", "HashSet", "HashMap", "ArrayList", "");
        final QuestionsList question4 = new QuestionsList("Która z poniższych metod jest używana do rozpoczęcia wykonywania nowego wątku w Kotlinie?", "startThread()", "runThread()", "begin()", "start()", "start()", "");
        final QuestionsList question5 = new QuestionsList("Który znak w Kotlinie jest używany do oznaczania opcjonalnych wartości (nullable)?", "?", "!", ".", ":", "?", "");

        questionsLists.add(question1);
        questionsLists.add(question2);
        questionsLists.add(question3);
        questionsLists.add(question4);
        questionsLists.add(question5);

        return questionsLists;
    }

    private static List<QuestionsList> csharpQuestionsPL(){

        final List<QuestionsList> questionsLists = new ArrayList<>();

        final QuestionsList question1 = new QuestionsList("Który z typów danych w C# jest uważany za typ prymitywny?", "int", "string", "Integer", "double", "int", "");
        final QuestionsList question2 = new QuestionsList("Który operator jest używany do porównywania dwóch wartości w C#?", "==", "=", "!=", "<>", "==", "");
        final QuestionsList question3 = new QuestionsList("Która z poniższych kolekcji w C# zapewnia szybkie wstawianie i pobieranie elementów?", "List", "LinkedList", "HashSet", "Dictionary", "List", "");
        final QuestionsList question4 = new QuestionsList("Która z poniższych metod jest używana do rozpoczęcia wykonywania nowego wątku w C#?", "startThread()", "runThread()", "begin()", "Start()", "Start()", "");
        final QuestionsList question5 = new QuestionsList("Która z poniższych struktur danych w C# jest niemutowalna?", "List", "ArrayList", "HashSet", "ImmutableList", "ImmutableList", "");

        questionsLists.add(question1);
        questionsLists.add(question2);
        questionsLists.add(question3);
        questionsLists.add(question4);
        questionsLists.add(question5);

        return questionsLists;
    }


    private static List<QuestionsList> kotlinQuestionsEN(){

        final List<QuestionsList> questionsLists = new ArrayList<>();

        final QuestionsList question1 = new QuestionsList("Which data type in Kotlin is considered a primitive type?", "Int", "String", "Integer", "Double", "Int", "");
        final QuestionsList question2 = new QuestionsList("Which operator is used to compare two values in Kotlin?", "==", "=", "!=", "<>", "==", "");
        final QuestionsList question3 = new QuestionsList("Which of the following collections in Kotlin provides fast insertion and retrieval of elements?", "ArrayList", "LinkedList", "HashSet", "HashMap", "ArrayList", "");
        final QuestionsList question4 = new QuestionsList("Which of the following methods is used to start executing a new thread in Kotlin?", "startThread()", "runThread()", "begin()", "start()", "start()", "");
        final QuestionsList question5 = new QuestionsList("Which character in Kotlin is used to denote nullable values?", "?", "!", ".", ":", "?", "");

        questionsLists.add(question1);
        questionsLists.add(question2);
        questionsLists.add(question3);
        questionsLists.add(question4);
        questionsLists.add(question5);

        return questionsLists;
    }

    private static List<QuestionsList> csharpQuestionsEN(){

        final List<QuestionsList> questionsLists = new ArrayList<>();

        final QuestionsList question1 = new QuestionsList("Which data type in C# is considered a primitive type?", "int", "string", "Integer", "double", "int", "");
        final QuestionsList question2 = new QuestionsList("Which operator is used to compare two values in C#?", "==", "=", "!=", "<>", "==", "");
        final QuestionsList question3 = new QuestionsList("Which of the following collections in C# provides fast insertion and retrieval of elements?", "List", "LinkedList", "HashSet", "Dictionary", "List", "");
        final QuestionsList question4 = new QuestionsList("Which of the following methods is used to start executing a new thread in C#?", "startThread()", "runThread()", "begin()", "Start()", "Start()", "");
        final QuestionsList question5 = new QuestionsList("Which of the following data structures in C# is immutable?", "List", "ArrayList", "HashSet", "ImmutableList", "ImmutableList", "");

        questionsLists.add(question1);
        questionsLists.add(question2);
        questionsLists.add(question3);
        questionsLists.add(question4);
        questionsLists.add(question5);

        return questionsLists;
    }



    public static List<QuestionsList> getQuestions(String selectedTopicName) {
        String language = LanguageDetector.getSystemLanguage();

        switch(selectedTopicName){
            case "Java":
                return language.equals("pl") ? javaQuestionsPL() : javaQuestionsEN();
            case "Android":
                return language.equals("pl") ? androidQuestionsPL() : androidQuestionsEN();
            case "Kotlin":
                return language.equals("pl") ? kotlinQuestionsPL() : kotlinQuestionsEN();
            default:
                return language.equals("pl") ? csharpQuestionsPL() : csharpQuestionsEN();
        }
    }
}
